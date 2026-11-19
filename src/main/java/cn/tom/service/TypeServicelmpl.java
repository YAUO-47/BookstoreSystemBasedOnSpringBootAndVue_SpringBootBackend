package cn.tom.service;

import cn.tom.dao.TypeMapper;
import cn.tom.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Service
public class TypeServicelmpl implements TypeService {
    @Autowired(required = false)
    TypeMapper typeMapper;

    public List<Type> findAllType() {
        return typeMapper.findAllType();
    }

    public int addType(Type t) {
        return typeMapper.addType(t);
    }

    public Type findOneType(int id) {
        return typeMapper.findOneType(id);
    }
    public int updateType(Type t) {
        return typeMapper.updateType(t);
    }

    public int removeType(int id) {
        return typeMapper.removeType(id);
    }
}
