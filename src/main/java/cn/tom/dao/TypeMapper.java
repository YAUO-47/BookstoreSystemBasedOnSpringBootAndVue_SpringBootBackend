package cn.tom.dao;

import cn.tom.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {
    public List<Type> findAllType();
    public Type findOneType(int id);
    public int addType(Type t);
    public int updateType(Type t);
    public int removeType(int id);
}
