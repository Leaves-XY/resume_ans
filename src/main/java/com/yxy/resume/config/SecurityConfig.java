package com.yxy.resume.config;

import com.yxy.resume.handler.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/11 10:49
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/register").permitAll()  // 主页面和登录注册页面无需登录
                .antMatchers("/user/**", "/seek/**", "/analysis/**").authenticated() // user，seek，analysis页面只需要登录
                .antMatchers("/recruit/**").hasAnyRole("RECRUITER", "ADMIN")  // recruit页面需要RECRUITER或者ADMIN
                .antMatchers("/resume/**", "/jobs/**","/admin/**").hasRole("ADMIN")  // admin,resume页面 jobs页面 需要ADMIN
                // 添加其他的 antMatchers 和对应的权限要求
                .and()
                .formLogin()
                .loginPage("/login")
                .failureHandler(customAuthenticationFailureHandler)  // 设置自定义的登录失败处理器
                .permitAll()
                // 添加其他的 formLogin 配置
                .and()
                .logout()
                .logoutSuccessUrl("/login"); // 注销后，将用户重定向到主页面
        // 添加其他的 logout 配置
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }



}