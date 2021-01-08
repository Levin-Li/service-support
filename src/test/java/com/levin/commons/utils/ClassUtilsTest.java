package com.levin.commons.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassUtilsTest {

    @Test
    void formatPackages() {

        List<String> packages = ClassUtils.formatPackages(" ", "..", " . . ", "com.levin.", "levin.", "com.levina", "com.levin.bb");

        System.out.println(packages);
    }
}