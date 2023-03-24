package com.jingnan.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: sublun
 * @Date: 2021/11/30 22:21
 */
@Configuration
public class SysConfig {
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
