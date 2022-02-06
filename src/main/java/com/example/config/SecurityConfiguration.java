package com.example.config;

import com.example.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //继承WebSecurityConfigurerAdapter，之后会进行配置

    @Resource
    UserAuthService service;

    @Resource
    PersistentTokenRepository repository;

    @Bean
    public PersistentTokenRepository jdbcRepository(@Autowired DataSource dataSource){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();  //使用基于JDBC的实现
        repository.setDataSource(dataSource);   //配置数据源
        //repository.setCreateTableOnStartup(true);   //启动时自动创建用于存储Token的表（建议第一次启动之后删除该行）
        return repository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)   //使用自定义的Service实现类进行验证
                .passwordEncoder(new BCryptPasswordEncoder());   //依然使用BCryptPasswordEncoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()   //首先需要配置哪些请求会被拦截，哪些请求必须具有什么角色才能访问
                .antMatchers("/static/**","/doLogin","/login").permitAll()    //静态资源，使用permitAll来运行任何人访问（注意一定要放在前面）
                .antMatchers("/index").hasAnyRole("user", "admin")   //index页面可以由user或admin访问
                .anyRequest().hasRole("admin")   //除了上面以外的所有内容，只能是admin访问
                .and()
                .formLogin()       //配置Form表单登陆
                .loginPage("/login")       //登陆页面地址（GET）
                .loginProcessingUrl("/doLogin")    //form表单提交地址（POST）
                .defaultSuccessUrl("/index")    //登陆成功后跳转的页面，也可以通过Handler实现高度自定义
                .permitAll()    //登陆页面也需要允许所有人访问
                .and()
                .logout()
                .logoutUrl("/logout")    //退出登陆的请求地址
                .logoutSuccessUrl("/login")    //退出后重定向的地址
                .and()
                .csrf().disable()
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenRepository(repository)
                .tokenValiditySeconds(60 * 60 * 24 * 7);  //Token的有效时间（秒）默认为14天;
    }
}