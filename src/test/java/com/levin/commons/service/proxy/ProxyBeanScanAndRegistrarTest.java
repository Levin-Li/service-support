package com.levin.commons.service.proxy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Method;
import java.util.Collections;

class ProxyBeanScanAndRegistrarTest {

    @Test
    void shouldFallbackToMetadataPackageWhenNoBasePackageSpecified() throws Exception {
        // 业务规则：未配置扫描包时，默认回退到配置类所在包。
        ProxyBeanScanAndRegistrar.ScanPair scanPair = new ProxyBeanScanAndRegistrar.ScanPair();

        invokeUpdateScanPairBasePackages(
                AnnotationMetadata.introspect(FallbackConfig.class),
                scanPair,
                new String[0],
                new Class[0]
        );

        String expectedPackage = FallbackConfig.class.getPackage().getName();
        Assertions.assertEquals(Collections.singleton(expectedPackage), scanPair.getScanPackages(),
                "未指定 basePackages 时，应自动扫描配置类所在包");
    }

    @Test
    void shouldNotForceMetadataPackageWhenBasePackageSpecified() throws Exception {
        // 业务规则：显式指定扫描包时，不应强制追加元数据包。
        ProxyBeanScanAndRegistrar.ScanPair scanPair = new ProxyBeanScanAndRegistrar.ScanPair();
        String customPackage = "com.custom.scan.pkg";

        invokeUpdateScanPairBasePackages(
                AnnotationMetadata.introspect(FallbackConfig.class),
                scanPair,
                new String[]{customPackage},
                new Class[0]
        );

        Assertions.assertTrue(scanPair.getScanPackages().contains(customPackage),
                "指定了自定义扫描包后，应包含该包");
        Assertions.assertFalse(scanPair.getScanPackages().contains(FallbackConfig.class.getPackage().getName()),
                "指定了自定义扫描包后，不应再自动追加配置类所在包");
    }

    private static void invokeUpdateScanPairBasePackages(
            AnnotationMetadata metadata,
            ProxyBeanScanAndRegistrar.ScanPair scanPair,
            String[] basePackages,
            Class<?>[] basePackageClasses
    ) throws Exception {
        Method method = ProxyBeanScanAndRegistrar.class.getDeclaredMethod(
                "updateScanPairBasePackages",
                AnnotationMetadata.class,
                ProxyBeanScanAndRegistrar.ScanPair.class,
                String[].class,
                Class[].class
        );
        method.setAccessible(true);
        method.invoke(null, metadata, scanPair, basePackages, basePackageClasses);
    }

    static class FallbackConfig {
    }
}
