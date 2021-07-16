package com.fuhao.cacahe;


import com.fuhao.utils.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @Author fuhao
 * @Date 2021/7/14 1:42 下午
 * @Version 1.0
 * 自定义Redis缓存实现
 */
public class RedisCache implements Cache {

    /**
     * 当前放入缓存的mapper的namespace
     */
    private final String id;

    /**
     * 要求必须存在带有string类型id参数的构造方法
     * @param id
     */
    public RedisCache(String id) {
        this.id = id;
    }

    /**
     * 返回cache唯一标识
     * @return
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 往缓存中添加数据   换成redis的实现 [redisTemplate] key可以为string，value只能是Object，所以不能使用stringRedisTemplate
     * RedisCache实例化是由mybatis实例化的，不是由spring工厂实例化的，无法直接在RedisCache里注入redisTemplate，所以需要有获取工厂的工具类
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {
        System.out.println("key:"+key.toString());
        System.out.println("value"+value);
        //通过application工具类获取redisTemplate
//        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        //使用redis中hash类型作为缓存存储模型 key存储namespace hashkey存储当前方法key value存储返回值value
        getRedisTemplate().opsForHash().put(id.toString(),getKeyToMD5(key.toString()),value);


    }

    /**
     * 从缓存中获取数据    换成redis的实现 [redisTemplate]
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        System.out.println("key:"+key.toString());
        //通过application工具类获取redisTemplate
//        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        //根据key从redis的hash类型中获取key
        return getRedisTemplate().opsForHash().get(id.toString(),getKeyToMD5(key.toString()));

    }

    /**
     * 这个方法为mybatis的保留方法，默认没有实现，后续版本可能会实现
     * @param o
     * @return
     */
    @Override
    public Object removeObject(Object o) {
        System.out.println("根据指定key删除缓存");
        return null;
    }

    @Override
    public void clear() {
        System.out.println("清空缓存");
        //通过application工具类获取redisTemplate
//        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //清空根据key(namespace)，清空缓存
        getRedisTemplate().delete(id.toString());
    }

    /**
     * 用来计算缓存的数量，缓存的击中率
     * @return
     */
    @Override
    public int getSize() {
        //获取hash中的key value数量
        return getRedisTemplate().opsForHash().size(id.toString()).intValue();
    }


    /**
     * 自行封装redisTemplate
     * @return
     */
    private RedisTemplate getRedisTemplate(){
        //通过application工具类获取redisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * 封装一个对key进行MD5处理的方法
     * @param key
     * @return
     */
    private String getKeyToMD5(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes(StandardCharsets.UTF_8));
    }
}
