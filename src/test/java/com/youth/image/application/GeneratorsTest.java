package com.youth.image.application;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Date;

public class GeneratorsTest {


    @Test
    void createUUIDStringTest(){
        String sample1 = Generators.stringUUID();
        String sample2 = Generators.prefixUUIDString();
    }
}
