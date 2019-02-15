package com.roy.miaosha.service;

import com.roy.miaosha.dao.UserDao;
import com.roy.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getUserById(int id){
        return userDao.getById(id);
    }
}
