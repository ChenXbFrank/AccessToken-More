package com.pls.accesstoken.service;

import com.pls.accesstoken.dao.UserDao;
import com.pls.accesstoken.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 81046 on 2018-08-02
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUserList(){
        return userDao.getAllUserList();
    }

    public List<User> getUserListByName(String name){
        return userDao.getUserListByName(name);
    }
}
