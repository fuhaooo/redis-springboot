package com.fuhao.service;

import com.fuhao.entity.Emp;

import java.util.List;

/**
 * @Author fuhao
 * @Date 2021/7/14 7:21 下午
 * @Version 1.0
 */
public interface EmpService {
    /**
     *
     * @return
     */
    List<Emp> findAll();
}
