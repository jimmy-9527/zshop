package com.ken.zshop.product;

import com.ken.zshop.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableFeignClients(basePackages = "com.ken.zshop.product.feign")
public class ProductApplicationTests {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void testCategoryPath(){
        Integer[] categorys = categoryService.findCategoryPath(292);
        log.info("testCategoryPath");
        log.info(Arrays.toString(categorys));
    }

    @Test
    public void contextLoads() {
    }

}
