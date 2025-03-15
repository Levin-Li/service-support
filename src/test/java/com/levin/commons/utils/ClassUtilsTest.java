package com.levin.commons.utils;

import com.levin.commons.service.domain.ApiResp;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassUtilsTest {

    @Test
    void formatPackages() {

        List<String> packages = ClassUtils.formatPackages(" ", "..", " . . ", "com.levin.", "levin.", "com.levina", "com.levin.bb");

        System.out.println(packages);
    }


    @Test
    void formatPackages2() {

        Schema annotation = ApiResp.class.getAnnotation(Schema.class);

        URL jarPath = Schema.class.getProtectionDomain().getCodeSource().getLocation();

        Schema.class.getClassLoader().getResource("");

        System.out.println(jarPath);
        System.out.println(jarPath.getPath());
    }
}