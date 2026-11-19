package cn.tom.service;

import cn.tom.dao.HotImgMapper;
import cn.tom.entity.HotImg;
import cn.tom.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotImgServicelmpl implements HotImgService {
    @Autowired(required = false)
    HotImgMapper hotImgMapper;

    public List<HotImg> findAllHot(){
        return  hotImgMapper.findAllHot();
    };
    public int addHot(HotImg h){
        return hotImgMapper.addHot(h);
    };
    public int updateHot(HotImg h){
        return hotImgMapper.updateHot(h);
    };
    public int removeHot(int id){
        return hotImgMapper.removeHot(id);
    };
}
