package com.fuhao.service;

import com.fuhao.dao.UserDAO;
import com.fuhao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author fuhao
 * @Date 2021/7/14 9:46 上午
 * @Version 1.0
 * @Transactional 注解控制事务
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    //这里报红不影响
    @Autowired
    private UserDAO userDAO;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findALL() {
        return userDAO.findAll() ;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findById(String id) {
        return userDAO.findById(id);
    }

    @Override
    public void delete(String id) {
        userDAO.delete(id);
    }

    @Override
    public void save(User user) {
        user.setId(UUID.randomUUID().toString());
        userDAO.save(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }
}
