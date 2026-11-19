package cn.tom.service;

import cn.tom.dao.UserMapper;
import cn.tom.entity.User;
import cn.tom.entity.UserP;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    public int  addUser(UserP userP){
        userP.setPassword(passwordEncoder.encode(userP.getPassword()));
        return userMapper.addUser(userP);
    };
    @CachePut(value = "xx",key="#map.get('id')")
    public User findOneIf(Map<String, Object> map){
        User x = userMapper.findOneIf(map);
        System.out.println(x);
        return x;
    };
    public int  updateUser(UserP userP){
        return userMapper.updateUser(userP);
    };
    public int  updateImg(@Param("id") int id, @Param("img") String img){
        return userMapper.updateImg(id,img);
    };
}
