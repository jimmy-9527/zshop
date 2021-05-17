package com.ken.zshop.feign;

import com.ken.zshop.product.ProductApplication;
import com.ken.zshop.common.utils.R;
import com.ken.zshop.product.ProductApplication;
import com.ken.zshop.product.entity.Blog;
import com.ken.zshop.product.feign.SearchFeign;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Test
    public void testSayHello2() {
        R r = searchFeign.sayHello2("张三");
        System.out.println(r);
    }

    @Test
    public void testGetBlog() {
        Blog blog = new Blog();
        blog.setId(1l);
        blog.setTitle("hello");
        blog.setContent("world");
        R r = searchFeign.getBlog(blog);
        System.out.println(r);
    }

    @Test
    public void testGetBlog2() {
        Blog blog = new Blog();
        blog.setId(1l);
        blog.setTitle("hello");
        blog.setContent("world");
        R r = searchFeign.getBlog2(blog, "xiaoliu");
        System.out.println(r);
    }
}
