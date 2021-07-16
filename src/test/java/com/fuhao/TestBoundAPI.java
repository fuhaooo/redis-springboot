package com.fuhao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

/**
 * @Author fuhao
 * @Date 2021/7/13 3:49 下午
 * @Version 1.0
 */

@SpringBootTest(classes = RedisSpringbootApplication.class)
public class TestBoundAPI {

     @Autowired
     private RedisTemplate redisTemplate;
     @Autowired
     private StringRedisTemplate stringRedisTemplate;

     //spring data 为了方便对redis的操作，因此提供bound api 简化操作
     @Test
     public void testBound(){
         redisTemplate.setKeySerializer(new StringRedisSerializer());
         redisTemplate.setHashKeySerializer(new StringRedisSerializer());

         //redisTemplate stringRedisTemplate 将一个多次操作的key进行绑定，对key进行绑定
//         stringRedisTemplate.opsForValue().set("name","fuhao");
//         stringRedisTemplate.opsForValue().append("name","hahaha");
//         String name = stringRedisTemplate.opsForValue().get("name");
//         System.out.println(name);

         //对字符串类型的key进行绑定，后续所有操作都是基于这个key的操作
         BoundValueOperations<String, String> nameValueOperations = stringRedisTemplate.boundValueOps("name");
         nameValueOperations.set("lisi");
         nameValueOperations.append("是一个好人");
         String name = nameValueOperations.get();
         System.out.println(name);

         //对list、set、zset、hash进行绑定
         BoundListOperations<String, String> listOperations = stringRedisTemplate.boundListOps("lists");
         listOperations.leftPushAll("张三","李四","王五");
         List<String> lists = listOperations.range(0, -1);
         lists.forEach(list -> System.out.println(list));

         //set
//         redisTemplate.boundSetOps();
//         stringRedisTemplate.boundSetOps();
         //zset
//         redisTemplate.boundZSetOps();
//         stringRedisTemplate.boundZSetOps();
         //hash
//         redisTemplate.boundHashOps();
//         stringRedisTemplate.boundHashOps();
     }
}
