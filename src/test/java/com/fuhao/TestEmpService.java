package com.fuhao;

import com.fuhao.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @Author fuhao
 * @Date 2021/7/14 7:25 下午
 * @Version 1.0
 */
@SpringBootTest(classes = RedisSpringbootApplication.class)
public class TestEmpService {

    @Autowired
    private EmpService empService;


    @Test
    public void testFindAll(){
        empService.findAll().forEach(emp -> System.out.println(emp));
        System.out.println("======================================");
        empService.findAll().forEach(emp -> System.out.println(emp));
    }

    @Test
    public void testmd5(){
        String key ="-874068520:2823693841:com.fuhao.dao.EmpDAO.findAll:0:2147483647:select id,name from t_emp;:SqlSessionFactoryBean";

        //利用spring框架提供的md5工具类
        String s = DigestUtils.md5DigestAsHex(key.getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
    }
}
