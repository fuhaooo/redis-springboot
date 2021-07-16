package com.fuhao.service;

import com.fuhao.entity.User;

import java.util.List;

/**
 * @Author fuhao
 * @Date 2021/7/14 9:45 上午
 * @Version 1.0
 */
public interface UserService {

    /**
     * 查询全部
     * @return
     */
    List<User> findALL();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 增加
     * @param user
     */
    void save(User user);

    /**
     * 更新
     * @param user
     */
    void update(User user);
}
