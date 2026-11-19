package cn.tom.controller;

import cn.tom.entity.LargeFile;
import cn.tom.entity.UserP;
import cn.tom.entity.XJson;
import cn.tom.entity.XResp;
import cn.tom.service.LargeFileService;
import cn.tom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequestMapping("/api/public")
@RestController
public class PublicController {
    @Autowired
    LargeFileService largeFileService;
    @Autowired
    UserService userService;

    @Autowired
     StringRedisTemplate stringRedisTemplate;

    @GetMapping("/showimg/{id}")    //  id  图片的 uuid 编号
    public void dispaly(@PathVariable("id") String id, HttpServletResponse response) {
        if (id == null) return;
        LargeFile lg = largeFileService.findOne(id);
        byte[] buf;
        try {
            buf = (byte[]) lg.getContent();
        } catch( NullPointerException e) {
            buf = new byte[1];
        }
        try {
            response.setContentType("image/jpeg");
            response.getOutputStream().write(buf);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/getCode")
    public void getCode(String phone){
        Random random = new Random();
        int randomNumber =  random.nextInt(999999)+100000;
        String string = String.valueOf(randomNumber);
        System.out.println(randomNumber);
        ValueOperations op = stringRedisTemplate.opsForValue();
        op.set(phone,string,60,TimeUnit.SECONDS);
    }

    @ResponseBody
    @PostMapping("/addUser")
    public XJson addUser(UserP user,
                         @RequestParam(value="mypic", required = false) MultipartFile file)  throws Exception{
        if (file != null && file.getSize() > 0) {
            String uuid = UUID.randomUUID().toString();//获取一个uuid
            uuid = uuid.replace("-", "");
            user.setImg(uuid);//把uuid放进要添加的学生对象内
            user.setRole("usr");
            user.setVip(0);
            user.setClosed(0);
            largeFileService.add(uuid, file.getOriginalFilename(), file.getBytes());//添加进文件数据库中
            userService.addUser(user);
            return XResp.pack(200, "注册成功", user);
        }
        return XResp.pack(401,"注册失败",null);
    }

    @PostMapping("/upload")    //  id  图片的 uuid 编号
    public String upload(@RequestParam("file") MultipartFile file) {
        // 处理文件上传逻辑
        // 可以在这里保存文件到服务器或者进行其他操作
        return "File uploaded successfully";
    }
}
