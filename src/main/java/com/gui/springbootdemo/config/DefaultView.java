package com.gui.springbootdemo.config;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName DefaultView
 * @Description TODO
 * @Author MI
 * @Date 2019/12/26 0:23
 * @Version 1.0
 */
public class DefaultView extends WebMvcConfigurationSupport {

    /**
     * 添加主页方法
     *
     * @param registry 主页注册器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("设置了主页");
        //设置主页
        registry.addViewController("/").setViewName("templates/index.html");
        //设置优先级
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        //将主页注册器添加到视图控制器中
        super.addViewControllers(registry);
    }
}
