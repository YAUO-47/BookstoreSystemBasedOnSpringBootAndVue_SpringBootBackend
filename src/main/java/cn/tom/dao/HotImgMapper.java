package cn.tom.dao;

import cn.tom.entity.HotImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotImgMapper {
    public List<HotImg> findAllHot();
    public int addHot(HotImg h);
    public int updateHot(HotImg h);
    public int removeHot(int id);
}
