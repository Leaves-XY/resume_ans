package com.yxy.resume.config;

import com.yxy.resume.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/10 21:58
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService  myUserDetailsService;

    /**
     * 定义了对应的访问权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/register").permitAll()  // 主页面和登录注册页面无需登录
                .antMatchers("/user/**", "/seek/**", "/analysis/**").authenticated() // user，seek，analysis页面只需要登录
                .antMatchers("/recruit/**").hasAnyRole("RECRUITER", "ADMIN")  // recruit页面需要RECRUITER或者ADMIN
                .antMatchers("/resume/**", "/jobs/**","/admin/**").hasRole("ADMIN")  // resume页面 jobs页面 需要ADMIN
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**
     * 密码编码器
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
