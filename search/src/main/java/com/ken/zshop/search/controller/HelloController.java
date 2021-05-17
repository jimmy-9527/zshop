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
}
