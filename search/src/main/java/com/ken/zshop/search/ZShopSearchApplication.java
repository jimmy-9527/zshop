package com.ken.zshop.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: sublun
 * @Date: 2021/4/25 18:30
 */
@SpringBootApplication
@MapperScan("com.ken.zshop.search.dao")
public class ZShopSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZShopSearchApplication.class);
    }
}
