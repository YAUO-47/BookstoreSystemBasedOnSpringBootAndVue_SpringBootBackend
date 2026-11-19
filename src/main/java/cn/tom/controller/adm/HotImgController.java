package cn.tom.controller.adm;

import cn.tom.entity.*;
import cn.tom.service.HotImgService;
import cn.tom.service.LargeFileService;
import cn.tom.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class HotImgController {
    Logger log = LoggerFactory.getLogger(HotImgController.class);
    @Autowired(required = false)
    HotImgService hotImgService;
    @Autowired(required = false)
    LargeFileService largeFileService;

    @GetMapping("/public/hot/findAll")
    public XJson findAll(){
        List<HotImg> lst = hotImgService.findAllHot();
        return XResp.pack(200,"",lst);
    }


    @PostMapping("/adm/hot/update")
    public XJson upHot(@RequestParam(value="pic", required = false) MultipartFile file,HotImg h)throws Exception{
        log.info("HotImg="+h);
        if(h.getId() == 0) {
            if (file != null && file.getSize() > 0) {
                String uuid = UUID.randomUUID().toString();//获取一个uuid
                uuid = uuid.replace("-", "");
                h.setPicture(uuid);//把uuid放进要添加的学生对象内
                largeFileService.add(uuid, file.getOriginalFilename(), file.getBytes());//添加进文件数据库中
                hotImgService.addHot(h);
            }
            List<HotImg> lst = hotImgService.findAllHot();
            return XResp.pack(200,"添加成功",lst);
        }
        System.out.println(file);
        if (file != null && file.getSize() > 0) {
            largeFileService.delete(h.getPicture());//删除文件数据库中的文件
            largeFileService.add(h.getPicture(), file.getOriginalFilename(), file.getBytes());//再次添加进去uuid不变实现修改
        }
        hotImgService.updateHot(h);
        List<HotImg> lst = hotImgService.findAllHot();
        return XResp.pack(200,"修改成功",lst);
    }

    @GetMapping("/adm/hot/remove")
    public XJson removeHot(int id){
        log.info("id="+id);
        hotImgService.removeHot(id);
        List<HotImg> lst = hotImgService.findAllHot();
        return XResp.pack(200,"删除成功",lst);
    }
}
