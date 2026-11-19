package cn.tom.service;

import cn.tom.entity.User;
import cn.tom.entity.UserP;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserService {
    public User findOneIf(Map<String, Object> map);
    public int  updateUser(UserP userP);
    public int  addUser(UserP userP);
    public int  updateImg(@Param("id") int id, @Param("img") String img);
}
