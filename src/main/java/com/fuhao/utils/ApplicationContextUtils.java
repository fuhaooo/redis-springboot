package com.fuhao.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author fuhao
 * @Date 2021/7/14 2:31 下午
 * @Version 1.0
 * 获取springboot创建好的工厂的工具类
 * @Configuration 为配置
 * @Component 为组件
 * @
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    /**
     * 保留下来的工厂
     */
    private static ApplicationContext applicationContext;

    /**
     * 将创建好的工厂以参数的形式传递给这个类
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    /**
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }


}
