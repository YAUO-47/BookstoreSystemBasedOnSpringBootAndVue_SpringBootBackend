package cn.tom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;
    //获取 AuthenticationManager 密码比对器
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //相同的密码 123456  , 加密的结果不一样
    }


    @Bean
        //@EnableWebSecurity  //加入到 Filter  才能让 http 参数变量识别到
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
                .antMatchers("/api/public/**")
                .permitAll()
                .antMatchers("/api/user/**")
                .permitAll()
                .antMatchers("/api/userc/**")
                .hasAuthority("usr")
                //需要放开的请求， 写前面
                .antMatchers("/api/adm/**")
                .hasAuthority("adm")
                .anyRequest().authenticated()
                //剩下请求， 登录成功就可以访问
                .and()
                .formLogin()
                .loginProcessingUrl("/api/login")
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtLoginFilter(authenticationConfiguration.getAuthenticationManager(),stringRedisTemplate))
                .addFilter(new JwtAuthoriztionFilter(authenticationConfiguration.getAuthenticationManager(),stringRedisTemplate))
                .csrf().disable();   //disable 禁止
        //able  能干   en-able   dis-able
        return http.build();
    }
}
