package com.zyy.shirodemo.realm;

import com.zyy.shirodemo.bean.Role;
import com.zyy.shirodemo.bean.UserTao;
import com.zyy.shirodemo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //认证通过之后 从SimpleAuthenticationInfo获得user对象
        UserTao user = (UserTao) principalCollection.getPrimaryPrincipal();
        //根据user对象的id 查询对象对应的所有角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取角色信息
        UserTao u = userService.findUserAndRolesById(user.getId());
        //遍历获得的对象里面的角色集合 得到用户的多个角色
        for (Role role:u.getRoles()) {
            System.out.println(role.getRoleName());
            info.addRole(role.getRoleName());
        }
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //在这里进行用户认证 认证密码和数据库的是否相同 通过之后 才能跳转到首页
        //authenticationToken里面就有主体传来的账号和密码
        /**
         * 1 从令牌里面获取账号 但是获取不到密码
         * 2 根据账号查询数据库是否存在这个用户
         */
//        String username = authenticationToken.getPrincipal().toString();
//        //模拟已存在的用户信息 根据用户名查找到的用户对象
//        User user = new User(1,"zyy","123","jinri");
//        //如果返回的用户对象为空
//        if (user==null){
//            return null;
//        }
//        /**
//         * 用获取到的数据库的用户和令牌里的用户名和密码比对
//         *
//         * 1 获取到的数据库用户对象
//         * 2 数据库用户对象的密码和令牌的比对
//         * 3 getName() 这里获取的名字是令牌里面的账号 this.getName 继承的AuthorizingRealm 从里面获取的方法
//         *
//         */
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassWord().toString(),getName());
        String userName = authenticationToken.getPrincipal().toString();
        UserTao user = userService.findUserByUserName(userName);
        if (user==null){
            return null;
        }
        //现在的参数传 对象 获取用户密码 盐 获取名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassWord().toString(),ByteSource.Util.bytes(user.getSalt()),getName());
        return info;
    }
}
