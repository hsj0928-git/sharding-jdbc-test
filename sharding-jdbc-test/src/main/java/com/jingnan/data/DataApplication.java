package com.jingnan.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: sublun
 * @Date: 2021/2/8 16:35
 */
@MapperScan("com/jingnan/data/mapper")
@SpringBootApplication
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class);
    }
}
