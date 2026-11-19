package cn.tom.service;

import cn.tom.entity.Type;

import java.util.List;

public interface TypeService {
    public List<Type> findAllType();
    public Type findOneType(int id);
    public int addType(Type t);
    public int updateType(Type t);
    public int removeType(int id);
}
