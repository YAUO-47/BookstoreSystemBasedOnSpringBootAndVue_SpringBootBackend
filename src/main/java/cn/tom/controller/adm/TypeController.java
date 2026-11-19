package cn.tom.controller.adm;

import cn.tom.entity.Type;
import cn.tom.entity.XJson;
import cn.tom.entity.XResp;
import cn.tom.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TypeController {
    Logger log = LoggerFactory.getLogger(TypeController.class);
    @Autowired(required = false)
    TypeService typeService;

    @GetMapping("/public/type/findAll")
    public XJson findAll(){
        List<Type> lst = typeService.findAllType();
        return XResp.pack(200,"",lst);
    }

    @PostMapping("/adm/type/add")
    public XJson addType(Type t){
        log.info("Type="+t);
        typeService.addType(t);
        List<Type> lst = typeService.findAllType();
        return XResp.pack(200,"添加成功",lst);
    }

    @PostMapping("/adm/type/update")
    public XJson updateType(Type t){
        log.info("Type="+t);
        typeService.updateType(t);
        List<Type> lst = typeService.findAllType();
        return XResp.pack(200,"修改成功",lst);
    }

    @PostMapping("/adm/type/remove")
    public XJson removeType(Type t){
        log.info("id="+t.getId());
        typeService.removeType(t.getId());
        List<Type> lst = typeService.findAllType();
        return XResp.pack(200,"删除成功",lst);
    }
}
