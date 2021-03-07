package com.zyy.shirodemo.dao;

import com.zyy.shirodemo.bean.UserTao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserDao {

    UserTao findUserByUserName(String userName);

    void addUser(UserTao user);

    UserTao findUserAndRolesById(int id);
}
