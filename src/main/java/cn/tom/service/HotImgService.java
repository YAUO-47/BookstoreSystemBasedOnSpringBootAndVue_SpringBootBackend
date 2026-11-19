package cn.tom.service;

import cn.tom.entity.HotImg;
import cn.tom.entity.Type;

import java.util.List;

public interface HotImgService {
    public List<HotImg> findAllHot();
    public int addHot(HotImg h);
    public int updateHot(HotImg h);
    public int removeHot(int id);
}
