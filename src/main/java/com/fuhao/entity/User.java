package com.fuhao.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author fuhao
 * @Date 2021/7/13 2:45 下午
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    /**
     * 需要跟数据库表的字段
     */
    private String id;
    private String name;
    private Integer age;
    private Date bir;
}
