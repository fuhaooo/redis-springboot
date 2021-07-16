package com.fuhao;

import com.sun.xml.internal.ws.resources.UtilMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author fuhao
 * @Date 2021/7/13 11:02 上午
 * @Version 1.0
 */
//启动springboot应用
@SpringBootTest(classes = RedisSpringbootApplication.class )
public class TestStringRedisTemplate {

    //注入springRedisTemplate
    //key和value都是字符串
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //操作redis中key相关
    @Test
    public void testKey(){
//        stringRedisTemplate.delete("name");//删除key
        Boolean hasKey = stringRedisTemplate.hasKey("name");//判断某个key是否存在
        System.out.println(hasKey);
        DataType keyType = stringRedisTemplate.type("name");//判断key所对应值的类型
        System.out.println(keyType);
        Set<String> allKeys = stringRedisTemplate.keys("*");//获取redis中的所有key
        System.out.println(allKeys);
        allKeys.forEach(keys -> System.out.println("keys:"+keys));
        Long nameExpire = stringRedisTemplate.getExpire("name");//获取key的超时时间,-1永不超时，-2永不存在
        System.out.println(nameExpire);
        stringRedisTemplate.randomKey();//在redis中随机获取key
        stringRedisTemplate.rename("name","name1");//修改key的名字，要求key必须存在，不存在报错
        stringRedisTemplate.renameIfAbsent("name","name1");
        stringRedisTemplate.move("name",1);
    }


    //操作redis中的字符串  opsForValue 实际操作的是redis中的String类型
    @Test
    public void testRedis() {
        stringRedisTemplate.opsForValue().set("name","付豪");//设置一个key，value
        stringRedisTemplate.opsForValue().set("name1","柿子树");
        String value = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name:"+value);
        stringRedisTemplate.opsForValue().set("18001111111","2357",90, TimeUnit.SECONDS);//设置一个key的超时时间

    }

    //操作redis中的list类型  opsForList
    @Test
    public void testList(){
        stringRedisTemplate.opsForList().leftPush("names","jack");//创建一个列表，并放入一个元素
        stringRedisTemplate.opsForList().leftPushAll("names","jack","mary","amy");//创建一个列表放多个元素
        List<String> names = new ArrayList<>();
        names.add("zhangsan");
        names.add("lisi");
        stringRedisTemplate.opsForList().leftPushAll("names",names);//创建一个列表放入多个元素
        List<String> stringList = stringRedisTemplate.opsForList().range("names", 0, -1);
        stringList.forEach(value -> System.out.println("value:"+value));
        stringRedisTemplate.opsForList().trim("names",1,3);//截取指定区间的List
    }


    //操作redis中的set类型  opsForSet
    @Test
    public void testSet(){
        stringRedisTemplate.opsForSet().add("sets","张三","李四","李四","王五");//设置set并放入多个元素
        Set<String> sets = stringRedisTemplate.opsForSet().members("sets");//查看sets中的成员
        sets.forEach(value -> System.out.println("value:"+value));
    }

    //操作redis中的ZSet类型 opsForZSet
    @Test
    public void testZSet(){
        stringRedisTemplate.opsForZSet().add("zsets","张三",100);//创建并放置元素
        Set<ZSetOperations.TypedTuple<String>> zsets = stringRedisTemplate.opsForZSet().rangeByScoreWithScores("zsets", 0, 1000);
        zsets.forEach(typedTuple -> {
            System.out.println("value:" + typedTuple.getValue());
            System.out.println("score:" + typedTuple.getScore());
        });
    }

    //操作redis中的Hash类型 opsForHash
    @Test
    public void testHash(){
        stringRedisTemplate.opsForHash().put("maps","name","张三");//创建一个hash类型，并放入 key value
        Map<String,String> maps = new HashMap<String,String>();
        maps.put("age","12");
        maps.put("bir","2021-09-12");
        stringRedisTemplate.opsForHash().putAll("maps",maps);//放入多个key value
        List<Object> values = stringRedisTemplate.opsForHash().multiGet("maps", Arrays.asList("name", "age"));
        values.forEach(value -> System.out.println(value));
        String value = (String ) stringRedisTemplate.opsForHash().get("maps","name");//获取hash中的某个key的值
        Set<Object> keys = stringRedisTemplate.opsForHash().keys("maps");//获取所有key
        List<Object> vals = stringRedisTemplate.opsForHash().values("maps");//获取所有value
    }


}
