package org.rkjavahub.assertj;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConditionalExecutionTests {

    @Test
    @EnabledOnOs(OS.WINDOWS)
    @Tag("Unit test")
    public void test1() {
        System.out.println("Test1 executed on Windows OS");
    }

    @Test
    @EnabledOnOs(OS.MAC)
    @Tag("Integration test")
    public void test2() {
        System.out.println("Test1 executed on MAC OS");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    public void test3() {
        System.out.println("Enabled for JRE 17");

    }
}
