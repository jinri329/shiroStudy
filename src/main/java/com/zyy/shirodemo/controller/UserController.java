package com.zyy.shirodemo.controller;


import com.zyy.shirodemo.bean.UserTao;
import com.zyy.shirodemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/user/login")
        public String login(String userName,String passWord){
            System.out.println(userName+passWord);
            //获取到主体
            Subject subject = SecurityUtils.getSubject();
            //将用户名和密码生成一个令牌
            UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);
            //令牌交给主体 -- 传给security manager -- 再调用realm
            subject.login(token);
            return "redirect:/index";
}

    @RequestMapping("/user/register")
    public String register(UserTao user){
        System.out.println(user);
        userService.addUser(user);
        return "redirect:/login";
    }

}
