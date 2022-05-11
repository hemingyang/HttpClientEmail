package com.chaoxing.jw.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * Description: 邮箱服务
 * @auther: hemingyang
 * @param:
 * @return:
 * @date: 2021/11/17 17:57
 */
@SpringBootApplication
@EnableScheduling
public class EmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }

}
