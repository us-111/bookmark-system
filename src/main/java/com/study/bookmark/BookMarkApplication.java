package com.study.bookmark;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.study.bookmark.mapper")
public class BookMarkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookMarkApplication.class, args);
    }

}
