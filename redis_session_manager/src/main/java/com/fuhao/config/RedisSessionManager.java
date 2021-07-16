package com.fuhao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author fuhao
 * @Date 2021/7/15 3:50 下午
 * @Version 1.0
 * @EnableRedisHttpSession 该注解会将整个应用中使用session的数据全部交给redis处理
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionManager {
}
