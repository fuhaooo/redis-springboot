package com.fuhao.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author fuhao
 * @Date 2021/7/14 7:01 下午
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class Emp implements Serializable {
    private String id;
    private String name;
}
