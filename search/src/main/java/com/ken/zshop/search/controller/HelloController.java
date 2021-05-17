package com.ken.zshop.search.controller;

import com.ken.zshop.search.model.Blog;
import com.ken.zshop.common.utils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: sublun
 * @Date: 2021/4/27 16:49
 */
@RestController
public class HelloController {
    @GetMapping("hello/{name}")
    public R sayHello(@PathVariable String name) {
        return R.ok("hello " + name);
    }

    @GetMapping("hello")
    public R sayHello2(String name) {
        return R.ok("hello " + name);
    }

    @PostMapping("/blog")
    public R getBlog(@RequestBody Blog blog) {
        blog.setComment("接收到blog对象");
        blog.setMobile("1111");
        return R.ok(blog);
    }

    @PostMapping("/blog2")
    public R getBlog2(@RequestBody Blog blog, String name) {
        blog.setComment(name);
        blog.setMobile("2222");
        return R.ok(blog);
    }
}
