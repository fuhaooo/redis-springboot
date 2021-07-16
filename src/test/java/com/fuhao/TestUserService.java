package com.fuhao;

import com.fuhao.entity.User;
import com.fuhao.service.UserService;
import org.apache.ibatis.cache.Cache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @Author fuhao
 * @Date 2021/7/14 9:49 上午
 * @Version 1.0
 */
@SpringBootTest(classes = RedisSpringbootApplication.class)
public class TestUserService  {

    @Autowired
    private UserService userService;

    @Test
    public void testFindAll(){

        userService.findALL().forEach(u -> System.out.println("user:"+u));
        System.out.println("==============================");
        userService.findALL().forEach(u -> System.out.println("user:"+u));

    }

    @Test
    public void testFindOne(){

        User byId = userService.findById("1");
        System.out.println("==============================");
        User byId1 = userService.findById("1");

    }

    @Test
    public void testDelete(){
        userService.delete("1");
    }

    @Test
    public void testSava(){
        User user = new User();
        user.setName("柿子树");
        user.setAge(23);
        user.setBir(new Date());
        userService.save(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId("1").setName("付豪2").setAge(24).setBir(new Date());
        userService.update(user);
    }




}
