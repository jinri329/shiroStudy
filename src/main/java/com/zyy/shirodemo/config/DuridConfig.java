package com.zyy.shirodemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DuridConfig {
    //1 德鲁伊对像
    //从配置文件加载配置信息
    @ConfigurationProperties(prefix = "spring.datasource")
    //加载方法上的注解 交给spring容器管理
    @Bean
    public DataSource druid() {
        return  new DruidDataSource();
    }

    //2 配置监控
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        //后台管理界面的登录账号
        initParams.put("loginUsername", "admin");
        //后台管理界面的登录密码
        initParams.put("loginPassword", "123456");
        //后台允许谁(ip)可以访问，为空或者为null时，表示允许所有访问
        initParams.put("allow", "");
        //后台拒绝谁访问
        //initParams.put("kuangshen", "192.168.1.20");表示禁止此ip访问
        //设置初始化参数
        bean.setInitParameters(initParams);
        return bean;
    }

    //3 filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        //exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        //"/*" 表示过滤所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
