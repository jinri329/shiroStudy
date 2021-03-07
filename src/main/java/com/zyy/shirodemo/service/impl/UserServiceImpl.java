package com.zyy.shirodemo.service.impl;

import com.zyy.shirodemo.bean.UserTao;
import com.zyy.shirodemo.dao.UserDao;
import com.zyy.shirodemo.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserTao findUserByUserName(String userName) {
        UserTao user = userDao.findUserByUserName(userName);
        if(user!=null) {
            return user;
        }
        return null;
    }

    @Override
    public void addUser(UserTao user) {
        //时间
        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        //随机盐
        String salt = UUID.randomUUID().toString().substring(0,4);//随机生成一个盐 使用uuid截取4位字符串
        user.setSalt(salt);
        user.setNum(1024);
        //密码加密
        Md5Hash newPassword = new Md5Hash(user.getPassWord(),salt,1024);
        user.setPassWord(newPassword.toHex());
        System.out.println(user);
        userDao.addUser(user);
    }

    @Override
    public UserTao findUserAndRolesById(int id) {
        UserTao user = userDao.findUserAndRolesById(id);
        return user;
    }
}
