package com.shuorigf.solarstaition;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestUtilsTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("测试开始");

    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试结束");
    }

    @Test
    public void getY_VALUE() {
        String[] strings=TestUtils.getY_VALUE(16.7,16.7,16.7);
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }

    }
}