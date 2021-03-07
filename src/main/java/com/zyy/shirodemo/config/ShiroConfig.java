package com.zyy.shirodemo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zyy.shirodemo.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        //
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        Map<String, String> map = new HashMap<String,String>();
        //map集合里面就是拦截规则 前面是拦截的网页 后面是规则 需要用户登录了才能访问网页
        map.put("/**","authc");
        map.put("/register","anon");//注册页面不拦截
        map.put("/user/login","anon");
        map.put("/user/register","anon");
        //这个方法是如果拦截了 就跳转到登录页面
        bean.setLoginUrl("/login");
        bean.setFilterChainDefinitionMap(map);
        //获取到一个SecurityManager
        bean.setSecurityManager(getDefaultWebSecurityManager());
        return bean;
    }
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //获取到realm
        securityManager.setRealm(getRealm());
        return securityManager;
    }
    @Bean
    public UserRealm getRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数;
        return hashedCredentialsMatcher;
    }

//    用于thymeleaf和shiro标签整合使用
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
