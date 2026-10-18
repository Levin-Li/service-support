package com.levin.commons.processor.mapstruct;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mapstruct.Mapper;
import org.mapstruct.ap.MappingProcessor;

import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class FieldAwareAccessorNamingStrategyTest {
    @TempDir Path root;

    @Test
    void bothAccessorStylesAndChainSettersMustCompileAndMapActualFields() throws Exception {
        for (boolean sourceBeanspec : new boolean[]{false, true}) {
            for (boolean targetBeanspec : new boolean[]{false, true}) {
                Path dir = Files.createDirectories(root.resolve(sourceBeanspec + "-" + targetBeanspec));
                List<Path> sources = new ArrayList<>();
                sources.add(write(dir, "Source.java", bean("Source", sourceBeanspec, false)));
                sources.add(write(dir, "Target.java", bean("Target", targetBeanspec, true)));
                sources.add(write(dir, "SampleMapper.java", "package probe; @org.mapstruct.Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.ERROR) public interface SampleMapper { @org.mapstruct.Mapping(target=\"eSigningProcessSnapshotList\", source=\"eSigningProcessSnapshotList\") Target map(Source source); }"));
                assertTrue(compile(dir, sources, true), "两种命名策略交叉映射必须通过 MapStruct 编译");
                try (URLClassLoader loader = loader(dir)) {
                    Class<?> sourceType = loader.loadClass("probe.Source");
                    Object source = sourceType.getConstructor().newInstance();
                    sourceType.getMethod(setter(sourceBeanspec), List.class).invoke(source, List.of("snapshot"));
                    sourceType.getMethod("setURL", String.class).invoke(source, "https://example.test");
                    sourceType.getMethod("setName", String.class).invoke(source, "normal");
                    Object mapper = loader.loadClass("probe.SampleMapperImpl").getConstructor().newInstance();
                    Object target = mapper.getClass().getMethod("map", sourceType).invoke(mapper, source);
                    assertEquals(List.of("snapshot"), target.getClass().getMethod(getter(targetBeanspec)).invoke(target));
                    assertEquals("https://example.test", target.getClass().getMethod("getURL").invoke(target));
                    assertEquals("normal", target.getClass().getMethod("getName").invoke(target));
                }
            }
        }
    }

    @Test
    void realLombokBasicAndBeanspecAccessorsMustMapWithoutChangingFieldNames() throws Exception {
        for (boolean sourceBeanspec : new boolean[]{false, true}) {
            for (boolean targetBeanspec : new boolean[]{false, true}) {
                Path dir = Files.createDirectories(root.resolve("lombok-" + sourceBeanspec + "-" + targetBeanspec));
                compileLombokBean(dir, "Source", sourceBeanspec);
                compileLombokBean(dir, "Target", targetBeanspec);
                Path mapperSource = write(dir, "SampleMapper.java", "package probe; @org.mapstruct.Mapper(unmappedTargetPolicy=org.mapstruct.ReportingPolicy.ERROR) public interface SampleMapper { @org.mapstruct.Mapping(target=\"eSigningProcessSnapshotList\", source=\"eSigningProcessSnapshotList\") Target map(Source source); }");
                assertTrue(compile(dir, List.of(mapperSource), true));
                try (URLClassLoader loader = loader(dir)) {
                    Class<?> sourceType = loader.loadClass("probe.Source");
                    Object source = sourceType.getConstructor().newInstance();
                    sourceType.getMethod(setter(sourceBeanspec), List.class).invoke(source, List.of("lombok"));
                    sourceType.getMethod("setURL", String.class).invoke(source, "acronym");
                    sourceType.getMethod("setName", String.class).invoke(source, "normal");
                    Object mapper = loader.loadClass("probe.SampleMapperImpl").getConstructor().newInstance();
                    Object target = mapper.getClass().getMethod("map", sourceType).invoke(mapper, source);
                    assertEquals(List.of("lombok"), target.getClass().getMethod(getter(targetBeanspec)).invoke(target));
                    assertEquals("acronym", target.getClass().getMethod("getURL").invoke(target));
                    assertEquals("normal", target.getClass().getMethod("getName").invoke(target));
                }
            }
        }
    }

    private void compileLombokBean(Path output, String name, boolean beanspec) throws Exception {
        Path sources = Files.createDirectories(output.resolve(name.toLowerCase()));
        Files.writeString(sources.resolve("lombok.config"), "config.stopBubbling = true\nlombok.accessors.capitalization = " + (beanspec ? "beanspec" : "basic") + "\n");
        Path source = write(sources, name + ".java", "package probe; @lombok.Getter @lombok.Setter @lombok.experimental.Accessors(chain=true) public class " + name + " { private java.util.List<String> eSigningProcessSnapshotList; private String URL; private String name; }");
        Path log = sources.resolve("lombok.log");
        Process process = new ProcessBuilder(Path.of(System.getProperty("java.home"), "bin", "javac").toString(),
                "-classpath", pathOf(lombok.Getter.class), "-processorpath", pathOf(lombok.Getter.class),
                "-processor", "lombok.launch.AnnotationProcessorHider$AnnotationProcessor", "-d", output.toString(), source.toString())
                .redirectErrorStream(true).redirectOutput(log.toFile()).start();
        if (!process.waitFor(30, java.util.concurrent.TimeUnit.SECONDS)) {
            process.destroyForcibly();
            fail("Lombok 编译超时");
        }
        assertEquals(0, process.exitValue(), Files.readString(log));
    }

    @Test
    void inheritedPrivateFieldMustBeMatchedAndComputedPropertyMustKeepJavaBeansName() throws Exception {
        Path dir = Files.createDirectories(root.resolve("inherited"));
        List<Path> sources = List.of(
                write(dir, "Base.java", "package probe; public class Base { private String eSigning; protected String read() { return eSigning; } protected void write(String value) { eSigning=value; } }"),
                write(dir, "Source.java", "package probe; public class Source extends Base { public String getESigning() { return read(); } public void setESigning(String value) { write(value); } public String getURL() { return \"computed\"; } }"),
                write(dir, "Target.java", "package probe; public class Target { private String eSigning; private String URL; public String geteSigning() { return eSigning; } public Target seteSigning(String value) { eSigning=value; return this; } public String getURL() { return URL; } public void setURL(String value) { URL=value; } }"),
                write(dir, "SampleMapper.java", "package probe; @org.mapstruct.Mapper(unmappedTargetPolicy=org.mapstruct.ReportingPolicy.ERROR) public interface SampleMapper { @org.mapstruct.Mapping(target=\"eSigning\", source=\"eSigning\") Target map(Source source); }"));
        assertTrue(compile(dir, sources, true));
        try (URLClassLoader loader = loader(dir)) {
            Class<?> sourceType = loader.loadClass("probe.Source");
            Object source = sourceType.getConstructor().newInstance();
            sourceType.getMethod("setESigning", String.class).invoke(source, "inherited");
            Object mapper = loader.loadClass("probe.SampleMapperImpl").getConstructor().newInstance();
            Object target = mapper.getClass().getMethod("map", sourceType).invoke(mapper, source);
            assertEquals("inherited", target.getClass().getMethod("geteSigning").invoke(target));
            assertEquals("computed", target.getClass().getMethod("getURL").invoke(target));
        }
    }

    @Test
    void defaultMapStructMustReproduceTheOriginalUnknownPropertyError() throws Exception {
        Path dir = Files.createDirectories(root.resolve("without-spi"));
        List<Path> sources = List.of(write(dir, "Source.java", bean("Source", false, false)),
                write(dir, "Target.java", bean("Target", false, false)),
                write(dir, "SampleMapper.java", "package probe; @org.mapstruct.Mapper public interface SampleMapper { @org.mapstruct.Mapping(target=\"eSigningProcessSnapshotList\") Target map(Source source); }"));
        assertFalse(compile(dir, sources, false), "未加载 SPI 时必须复现字段名与属性名不一致的编译错误");
    }

    private boolean compile(Path dir, List<Path> sources, boolean enableSpi) throws Exception {
        String processorPath = pathOf(MappingProcessor.class);
        if (enableSpi) {
            Path jar = dir.resolve("field-naming-spi.jar");
            try (JarOutputStream out = new JarOutputStream(Files.newOutputStream(jar))) {
                String name = FieldAwareAccessorNamingStrategy.class.getName().replace('.', '/') + ".class";
                out.putNextEntry(new JarEntry(name));
                try (var stream = FieldAwareAccessorNamingStrategy.class.getClassLoader().getResourceAsStream(name)) {
                    assertNotNull(stream);
                    stream.transferTo(out);
                }
                out.closeEntry();
                String registration = "META-INF/services/org.mapstruct.ap.spi.AccessorNamingStrategy";
                out.putNextEntry(new JarEntry(registration));
                // 使用随 service-support JAR 打包的实际 SPI 注册文件。
                out.write(Files.readAllBytes(Path.of("src/main/processor").resolve(registration)));
                out.closeEntry();
            }
            processorPath += java.io.File.pathSeparator + jar;
        }
        // 独立 javac 进程避免测试 JVM 已加载的 MapStruct 类绕过 processorpath 的 SPI。
        List<String> command = new ArrayList<>(List.of(
                Path.of(System.getProperty("java.home"), "bin", "javac").toString(),
                "-classpath", pathOf(Mapper.class) + java.io.File.pathSeparator + dir, "-processorpath", processorPath,
                "-processor", MappingProcessor.class.getName(), "-d", dir.toString(), "-s", dir.toString()));
        sources.forEach(source -> command.add(source.toString()));
        Path diagnostics = dir.resolve("javac.log");
        Process process = new ProcessBuilder(command).redirectErrorStream(true).redirectOutput(diagnostics.toFile()).start();
        if (!process.waitFor(30, java.util.concurrent.TimeUnit.SECONDS)) {
            process.destroyForcibly();
            fail("MapStruct 编译超时");
        }
        boolean success = process.exitValue() == 0;
        String output = Files.readString(diagnostics);
        if (enableSpi) assertTrue(success, output);
        else assertTrue(output.contains("Unknown property"), output);
        return success;
    }

    private static String bean(String name, boolean beanspec, boolean chain) {
        String result = chain ? name : "void";
        String returns = chain ? "return this;" : "";
        return "package probe; public class " + name + " { private java.util.List<String> eSigningProcessSnapshotList; private String URL; private String name;"
                + " public java.util.List<String> " + getter(beanspec) + "() { return eSigningProcessSnapshotList; }"
                + " public " + result + " " + setter(beanspec) + "(java.util.List<String> value) { eSigningProcessSnapshotList=value; " + returns + " }"
                + " public String getURL() { return URL; } public " + result + " setURL(String value) { URL=value; " + returns + " }"
                + " public String getName() { return name; } public " + result + " setName(String value) { name=value; " + returns + " } }";
    }
    private static String getter(boolean beanspec) { return beanspec ? "geteSigningProcessSnapshotList" : "getESigningProcessSnapshotList"; }
    private static String setter(boolean beanspec) { return beanspec ? "seteSigningProcessSnapshotList" : "setESigningProcessSnapshotList"; }
    private static String pathOf(Class<?> type) throws Exception { return Path.of(type.getProtectionDomain().getCodeSource().getLocation().toURI()).toString(); }
    private Path write(Path dir, String name, String source) throws Exception { Path file=dir.resolve(name); Files.writeString(file, source); return file; }
    private URLClassLoader loader(Path dir) throws Exception { return new URLClassLoader(new java.net.URL[]{dir.toUri().toURL()}, getClass().getClassLoader()); }
}
