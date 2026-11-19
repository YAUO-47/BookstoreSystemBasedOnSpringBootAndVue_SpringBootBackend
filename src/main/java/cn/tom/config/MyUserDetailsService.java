package cn.tom.config;

import cn.tom.dao.UserMapper;
import cn.tom.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sun.text.normalizer.UnicodeSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MyUserDetailsService implements UserDetailsService {
    Logger log2 = LoggerFactory.getLogger(MyUserDetailsService.class);
    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired(required = false)
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log2.info("网页登陆的账号=" + username);
        String aa[] = username.split(":");
        if (aa.length >= 2) {
            username = aa[1];
        }
        if ("1".equals(aa[0])) {
            Map<String, Object> map = new HashMap<>();
            map.put("phone",username);
            User x = userMapper.findOneIf(map);
            log2.info("User=" + x);
            if (x == null) throw new UsernameNotFoundException("账号不存在");
            //验证码登录
            ValueOperations<String,String> op = stringRedisTemplate.opsForValue();
            String pass = op.get(username);
            User user = x;
            user.setPassword(passwordEncoder.encode(pass));
            return user;
        } else {
            //密码登录
            Map<String, Object> map = new HashMap<>();
            map.put("account", username);
            User x = userMapper.findOneIf(map);
            log2.info("User=" + x);
            if (x == null) throw new UsernameNotFoundException("账号不存在");
            return x;
        }
    }
}
