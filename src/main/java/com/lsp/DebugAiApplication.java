package com.lsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.lsp.mapper") //扫描mapper接口路径
public class DebugAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DebugAiApplication.class, args);
    }

}
