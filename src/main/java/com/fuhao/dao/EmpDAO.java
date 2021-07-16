package com.fuhao.dao;

import com.fuhao.entity.Emp;


import java.util.List;

/**
 * @Author fuhao
 * @Date 2021/7/14 7:16 下午
 * @Version 1.0
 */
public interface EmpDAO {
    List<Emp> findAll();
}
