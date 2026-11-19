package cn.tom.controller.adm;

import cn.tom.entity.*;
import cn.tom.service.BookService;
import cn.tom.service.LargeFileService;
import cn.tom.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")//方便前端跨域,都带上api
public class BookController {
    @Autowired(required = false)
    BookService bookService;
    @Autowired(required = false)
    LargeFileService largeFileService;
    @Autowired
    TypeService typeService;

    @GetMapping("/public/book/findAll")
    public XJson findAll(Integer start, Integer pageline, Integer typeid) {//根据书本类型分页查找所有的书,Integer可以接收null
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> returnMap = new HashMap<>();
        if (typeid != 0 && typeid != null) {
            if (typeService.findOneType(typeid).getPid() == 0) {//pid等于0则代表是一级类型,查找一级类型下的所有书
                map.put("start", start);
                map.put("pageline", pageline);
                map.put("pid", typeid);
                List<Book> lst = bookService.findAllBookByPid(map);
                int count = bookService.getCountByMainType(typeid);
                returnMap.put("lst", lst);
                returnMap.put("count", count);
                return XResp.pack(200, "", returnMap);
            }
        }
        //pid不等于0则代表是二级类型,根据类型查找该类型的所有书
        map.put("start", start);
        map.put("pageline", pageline);
        map.put("typeid", typeid);
        Map<String, Object> countMap = new HashMap<>();
        List<Book> lst = bookService.findAllBook(map);
        countMap.put("typeid", typeid);
        int count = bookService.getCount(countMap);
        returnMap.put("lst", lst);
        returnMap.put("count", count);
        return XResp.pack(200, "", returnMap);
    }

    @GetMapping("/public/book/findAllByKey")
    public XJson findAll(Integer start, Integer pageline, String key) {//根据搜索关键词模糊查找书籍
        System.out.println(key);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> returnMap = new HashMap<>();
        map.put("start", start);
        map.put("pageline", pageline);
        map.put("key", key);
        Map<String, Object> countMap = new HashMap<>();
        countMap.put("key", key);
        List<Book> lst = bookService.findAllBookByKey(map);
        int count = bookService.getCount(countMap);
        for (int i=0;i<lst.size();i++){
            lst.get(i).setName(getRed(lst.get(i).getName(),key));//给每一本书的标题跟简介加上高亮显示
            lst.get(i).setInfo(getRed(lst.get(i).getInfo(),key));
        }
        returnMap.put("lst", lst);
        returnMap.put("count", count);
        return XResp.pack(200, "", returnMap);
    }

    public String getRed(String input, String key) {//使用正则表达式将颜色样式拼接到关键词前面
        Pattern pattern = Pattern.compile(key, Pattern.CASE_INSENSITIVE);//创建正则表达式对象,Pattern.CASE_INSENSITIVE
        // 用来指定忽略大小写
        Matcher matcher = pattern.matcher(input);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, "<span style=\\\"color:red;\\\">$0</span>");
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    @GetMapping("/adm/book/getCountByMainType")
    public XJson getCountByMainType(Integer typeid) {//计算行数用的,后面使用了returnMap出去的方式,这个好像有地方用好像又没有没删除
        int count = bookService.getCountByMainType(typeid);
        return XResp.pack(200, "", count);
    }

    @GetMapping("/adm/book/add")
    public XJson findAll(Book b) {
        bookService.addBook(b);
        return XResp.pack(200, "添加成功", null);
    }

    @GetMapping("/public/book/findOne")//查找一本书
    public XJson findOne(Integer id) {
        BookT book = bookService.findOneBook(id);
        return XResp.pack(200, "", book);
    }


    @PostMapping("/adm/book/update")//修改
    public XJson updateBook(@RequestParam(value = "picture", required = false) MultipartFile file, Book b) throws Exception {
        if (b.getId() == 0) {//如果获取到的的id是0默认为添加
            if (file != null && file.getSize() > 0) {
                String uuid = UUID.randomUUID().toString();//获取一个uuid
                uuid = uuid.replace("-", "");
                b.setPic(uuid);//把uuid放进要添加的对象内
                largeFileService.add(uuid, file.getOriginalFilename(), file.getBytes());//添加进文件数据库中
                bookService.addBook(b);
            }
            return XResp.pack(200, "", null);
        }
        //如果获取到的的id不是0则开始修改
        if (file != null && file.getSize() > 0) {
            largeFileService.delete(b.getPic());//删除文件数据库中的文件
            largeFileService.add(b.getPic(), file.getOriginalFilename(), file.getBytes());//再次添加进去uuid不变实现修改
        }
        bookService.updateBook(b);
        return XResp.pack(200, "", null);
    }


}
