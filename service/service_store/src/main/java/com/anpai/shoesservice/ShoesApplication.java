package com.anpai.shoesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xiesongzhi
 * @Description:
 * @create: 2020/6/6 20:07
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.anpai"})
public class ShoesApplication {
    public static void main(String[] args) {
        System.out.println("我是分支");
        System.out.println("创建主干");
        SpringApplication.run(ShoesApplication.class, args);
    }
}
