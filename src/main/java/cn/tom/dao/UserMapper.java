package cn.tom.dao;

import cn.tom.entity.User;
import cn.tom.entity.UserP;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper {
    public User findOneIf(Map<String, Object> map);
    public int  updateUser(UserP userP);
    public int  updateImg(@Param("id") int id, @Param("img") String img);
    public int  addUser(UserP userP);
}
