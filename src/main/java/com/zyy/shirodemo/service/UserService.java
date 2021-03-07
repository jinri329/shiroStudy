package com.zyy.shirodemo.service;

import com.zyy.shirodemo.bean.UserTao;

public interface UserService {
    /**
     * 根据用户名查找用户信息
     * @param userName
     * @return
     */
    UserTao findUserByUserName(String userName);

    /**
     * 注册用户 添加用户到数据库
     * @param user
     */
    void addUser(UserTao user);

    UserTao findUserAndRolesById(int id);
}
