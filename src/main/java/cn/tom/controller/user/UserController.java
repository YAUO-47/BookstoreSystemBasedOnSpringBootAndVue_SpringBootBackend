package cn.tom.controller.user;

import cn.tom.entity.User;
import cn.tom.entity.UserP;
import cn.tom.entity.XJson;
import cn.tom.entity.XResp;
import cn.tom.service.LargeFileService;
import cn.tom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/userc/my")
public class UserController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    LargeFileService largeFileService;

    @GetMapping("/findOne")
    public XJson findOne(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        User x = userService.findOneIf(map);
        return XResp.pack(200,"",x);
    }

    @PostMapping("/updatePwd/{oldP}/{newP}")
    public XJson updatePwd(@PathVariable("oldP") String oldP,@PathVariable("newP") String newP) {
        Object who = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) who;
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        User x = userService.findOneIf(map);
        boolean matches = passwordEncoder.matches(oldP,x.getPassword());
        if(matches == true){
            if(newP.equals(oldP)) {
                return XResp.pack(401,"旧密码与新密码不能相同",null);
            }
            UserP userP = new UserP();
            userP.setId(x.getId());
            String pwd = passwordEncoder.encode(newP);
            userP.setPassword(pwd);
            userService.updateUser(userP);
            return XResp.pack(200,"修改密码成功",null);
        }
        return XResp.pack(401,"旧密码输入错误",null);
    }

    @PostMapping("/updateMsg")
    public XJson updateMsg(UserP userP) {
       userService.updateUser(userP);
       return XResp.pack(200,"修改成功",null);
    }

    @ResponseBody
    @PostMapping("/updateHead")
    public XJson doUpdate(@RequestParam(value="mypic", required = false) MultipartFile file)  throws Exception{
        if (file == null && file.getSize() < 10) {
            return XResp.pack(401,"图片上传有误,更新失败","");
        }
        Object who = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) who;
        if(user == null){
            return XResp.pack(401,"更新失败,登录过期","");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        User newUser = userService.findOneIf(map);
        if(newUser == null){
            return XResp.pack(401,"该用户不存在","");
        }
        String uuid = newUser.getImg();
        if(uuid != null && uuid.length()>0){
            largeFileService.delete(uuid);//删除文件数据库中的文件
        }else{
            uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-","");
            userService.updateImg(newUser.getId(),uuid);
        }

        largeFileService.add(uuid, file.getOriginalFilename(), file.getBytes());//再次添加进去uuid不变实现修改
       uuid = uuid+"?t="+System.currentTimeMillis()%100000;
        return XResp.pack(200,"更新成功",uuid);
    }


}
