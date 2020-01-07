package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录。跳转到security默认的登录表单页
        // http.httpBasic() //basic登录
        .and()
        .authorizeRequests() // 对请求授权
        .antMatchers("/**").permitAll() //允许所有人访问/noAuth
        .anyRequest() // 任何请求
        .authenticated()// 需要身份认证
        ; 
        
        //添加這行否則德魯伊登不上
        http.csrf().ignoringAntMatchers("/druid/*");
    
        http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("level1/**").hasRole("vip")
        .antMatchers("level2/**").hasRole("vip3")
        .antMatchers("level3/**").hasRole("vip2");
    
    
    }
    
   
    //認證！
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

      auth.inMemoryAuthentication()
              .withUser("user").password("user").roles("USER")
              .and()
             .withUser("admin").password("admin").roles("ADMIN");
      auth.jdbcAuthentication();

     // auth.authenticationProvider(custAuthenticationProvider);

    }

}
