package com.xieyv.lc4j;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class OtherTest {
    @Test
    public void timeTest() {
        System.out.println(new Date().toString());
    }
}
