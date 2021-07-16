package com.fuhao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
@MapperScan("com.fuhao.dao")
public class RedisSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisSpringbootApplication.class, args);
    }

}
