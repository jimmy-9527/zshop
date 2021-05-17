package com.ken.zshop.feign;

import com.ken.zshop.product.ProductApplication;
import com.ken.zshop.common.utils.R;
import com.ken.zshop.product.ProductApplication;
//import com.ken.zshop.product.entity.Blog;
import com.ken.zshop.product.feign.SearchFeign;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: sublun
 * @Date: 2021/4/27 17:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class SearchFeignTest {
    @Autowired
    private SearchFeign searchFeign;

    @Test
    public void testSayHello() {
        R r = searchFeign.sayHello("tom");
        System.out.println(r);
    }
}
