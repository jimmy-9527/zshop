package com.ken.zshop.search.controller;

import com.ken.zshop.search.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HelloController2 {
    //可以使用SpringTemplateEngine，生成静态页面。
    @Autowired
    private SpringTemplateEngine engine;

    @GetMapping("/html/hello")
    //返回String类型，默认使用springmvc的视图解析器。
    public String sayHello(Model model) {
        //向content对象中添加模板使用的变量
        model.addAttribute("hello", "hello world!");
        model.addAttribute("html","<h1>hello</h1>");
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"张三1", "北京", new Date()));
        userList.add(new User(2,"张三2", "北京", new Date()));
        userList.add(new User(3,"张三3", "北京", new Date()));
        userList.add(new User(4,"张三4", "北京", new Date()));
        userList.add(new User(5,"张三5", "北京", new Date()));
        userList.add(new User(6,"张三6", "北京", new Date()));
        userList.add(new User(7,"张三7", "北京", new Date()));
        model.addAttribute("userList", userList);
        model.addAttribute("flag", false);
        //返回模板文件的名称即可
        return "hello";
    }
}
