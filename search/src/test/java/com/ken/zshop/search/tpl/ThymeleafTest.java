package com.ken.zshop.search.tpl;
//
//import com.ken.zshop.search.model.User;
//import org.junit.Test;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//
//import java.io.FileWriter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class ThymeleafTest {
//    @Test
//    public void testThymeleaf() throws Exception {
//        //创建一个基于classpath的一个加载器
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        //设置加载器的属性，前缀和后缀
//        templateResolver.setPrefix("templates/");
//        templateResolver.setSuffix(".html");
//        //创建一个模板引擎
//        TemplateEngine engine = new TemplateEngine();
//        engine.setTemplateResolver(templateResolver);
//        //创建一个Context对象，相当于是一个map，向模板传递数据需要使用。
//        Context context = new Context();
//        //向content对象中添加模板使用的变量
//        context.setVariable("hello", "hello world!");
//        context.setVariable("html","<h1>hello</h1>");
//        List<User> userList = new ArrayList<>();
//        userList.add(new User(1,"张三1", "北京", new Date()));
//        userList.add(new User(2,"张三2", "北京", new Date()));
//        userList.add(new User(3,"张三3", "北京", new Date()));
//        userList.add(new User(4,"张三4", "北京", new Date()));
//        userList.add(new User(5,"张三5", "北京", new Date()));
//        userList.add(new User(6,"张三6", "北京", new Date()));
//        userList.add(new User(7,"张三7", "北京", new Date()));
//        context.setVariable("userList", userList);
//        context.setVariable("flag", false);
//        //渲染模板，模板所在的位置，使用的context对象，静态文件生成的路径
//        FileWriter writer = new FileWriter("C:/Users/Ken/Desktop/hello.html");
//        engine.process("hello", context, writer);
//    }
//}
