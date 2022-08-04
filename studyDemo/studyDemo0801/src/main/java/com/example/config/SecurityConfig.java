package com.example.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author :zoutongkun
 * @date :2022/8/2 1:16 上午
 * @description :
 * @modyified By:
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll();

        http.formLogin();
        http.csrf().disable();
        http.logout().logoutSuccessUrl("/");
        http.rememberMe();
    }
}
