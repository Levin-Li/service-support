package com.levin.commons.processor;

import jakarta.persistence.JoinColumn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JpaEntityClassProcessorTest {

    @TempDir
    Path output;

    @Test
    void compilesJoinColumnNamesAndPreservesTheirConstants() throws Exception {
        var result = compile("""
                @JoinColumn(name = "literal") Object literalRelation;
                @JoinColumn(name = E_ExampleEntity.ownerId) Object generatedRelation;
                @JoinColumn(name = PREFIX + (E_ExampleEntity.ownerId + "_ext")) Object expressionRelation;
                @JoinColumn Object implicitRelation;
                """);

        assertTrue(result.success(), result.diagnostics().toString());
        try (var loader = new URLClassLoader(new java.net.URL[]{output.toUri().toURL()}, getClass().getClassLoader())) {
            Class<?> entity = loader.loadClass("sample.ExampleEntity");
            Class<?> constants = loader.loadClass("sample.E_ExampleEntity");

            assertColumn(entity, constants, "literalRelation", "literal");
            assertColumn(entity, constants, "generatedRelation", "ownerId");
            assertColumn(entity, constants, "expressionRelation", "prefix_ownerId_ext");
            assertThrows(NoSuchFieldException.class, () -> constants.getField("T_implicitRelation"));
        }
    }

    @Test
    void invalidGeneratedFieldRemainsACompilationError() throws Exception {
        var result = compile("@JoinColumn(name = E_ExampleEntity.missing) Object invalidRelation;");

        assertFalse(result.success());
        assertTrue(result.diagnostics().stream().anyMatch(diagnostic ->
                diagnostic.getKind() == Diagnostic.Kind.ERROR && diagnostic.getMessage(null).contains("missing")),
                result.diagnostics().toString());
    }

    private void assertColumn(Class<?> entity, Class<?> constants, String field, String expected) throws Exception {
        assertEquals(expected, entity.getDeclaredField(field).getAnnotation(JoinColumn.class).name());
        assertEquals(expected, constants.getField("T_" + field).get(null));
    }

    private Compilation compile(String mappings) throws Exception {
        Path source = Files.writeString(output.resolve("ExampleEntity.java"), """
                package sample;

                import jakarta.persistence.Entity;
                import jakarta.persistence.JoinColumn;

                @Entity
                public class ExampleEntity {
                    private static final String PREFIX = "prefix_";
                    String ownerId;
                    %s
                }
                """.formatted(mappings));
        var compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        try (var files = compiler.getStandardFileManager(diagnostics, null, null)) {
            var task = compiler.getTask(null, files, diagnostics,
                    List.of("-classpath", System.getProperty("java.class.path"), "-d", output.toString()),
                    null, files.getJavaFileObjects(source));
            task.setProcessors(List.of(new JpaEntityClassProcessor()));
            return new Compilation(task.call(), diagnostics.getDiagnostics());
        }
    }

    private record Compilation(boolean success, List<Diagnostic<? extends JavaFileObject>> diagnostics) {
    }
}
