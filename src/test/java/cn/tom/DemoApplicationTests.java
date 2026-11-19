package cn.tom;

import cn.tom.entity.Book;
import cn.tom.entity.BookT;
import cn.tom.service.BookService;
import cn.tom.service.UserService;
import com.sun.javafx.collections.MappingChange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
//        System.out.println("UserService 类型:"+userService.getClass());
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", 5);
//        System.out.println("第一次查找");
//        userService.findOneIf(map);
//        System.out.println("第一次查找");
//        bookService.findOneBook(3);
//        System.out.println("第二次查找");
//        bookService.findOneBook(3);
//        Book oneBook =new Book();
//        oneBook.setId(3);
//        oneBook.setName("Java从项目实战到入门12");
//        bookService.updateBook(oneBook);
        Map<String, Object> map = new HashMap<>();
        map.put("start", null);
        map.put("pageline",null);
        map.put("pid",null);
        bookService.findAllBook(map);
    }

}
