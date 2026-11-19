package cn.tom.controller;

import cn.tom.entity.XJson;
import cn.tom.entity.XResp;
import cn.tom.entity.User;
import cn.tom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/info")
    public XJson userInfo(){
        Object who = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) who;
        return XResp.pack(200, "", user);
    }


}
