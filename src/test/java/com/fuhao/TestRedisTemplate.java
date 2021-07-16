package com.fuhao;

import com.fuhao.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Date;
import java.util.UUID;

/**
 * @Author fuhao
 * @Date 2021/7/13 2:55 下午
 * @Version 1.0
 */
@SpringBootTest(classes = RedisSpringbootApplication.class)
public class TestRedisTemplate {

    //注入RedisTemplate  key和value都是对象Object,无法直接存储到redis,必须将对象序列化
    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testRedisTemplate(){

        /**
         * redisTemplate对象中key和value的序列化方式都是 JdkSerializationRedisSerializer、
         *      key : String
         *      value : Object
         *      修改默认的key的序列化方案，改为 StringRedisSerializer 序列化方案，不然终端无法对序列化后的key进行操作了
         *      redisTemplate.setKeySerializer(new StringRedisSerializer());
         */
        //修改key的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //修改hash key的序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        User user = new User();
        user.setId(UUID.randomUUID().toString()).setName("fuhao").setAge(22).setBir(new Date());
        redisTemplate.opsForValue().set("user", user);//redis进行设置，对象需要进过序列化
        User user1 = (User) redisTemplate.opsForValue().get("user");
        System.out.println(user1);

        RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        System.out.println(keySerializer);

        redisTemplate.opsForList().leftPush("list",user);
        redisTemplate.opsForSet().add("set",user);
        redisTemplate.opsForZSet().add("zset",user,10);
        redisTemplate.opsForHash().put("map","name",user);


    }
}
