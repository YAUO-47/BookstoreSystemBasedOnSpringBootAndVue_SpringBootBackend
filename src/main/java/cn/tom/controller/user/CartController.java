package cn.tom.controller.user;

import cn.tom.entity.*;
import cn.tom.service.BookService;
import cn.tom.service.CartService;
import cn.tom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CartController {
    Logger log = LoggerFactory.getLogger(CartController.class);
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @GetMapping("/public/cart/addCart/{bookid}/{uid}")//将书籍加入到当前用户的购物车中
    public XJson addCart(@PathVariable("bookid") int bookid,@PathVariable("uid") int uid ) {
        //判断是否登录   ....
        Map<String, Object> map = new HashMap<>();
        map.put("id", uid);
        User user = userService.findOneIf(map);
        if (user == null){
            return XResp.pack(401, "未登录,请先登录", null);
        }
        if(user.getRole().equals("usr")){
            Cart cartOne = cartService.findCartOne(bookid,uid);
            BookT oneBook = bookService.findOneBook(bookid);
            if(cartOne == null) {
                Cart cart = new Cart();
                cart.setBook(oneBook);
                cart.setUid(user.getId());
                cart.setNum(1);
                cart.setPrice(oneBook.getPrice());
                cart.setTcreate(new Date());
                cartService.addCart(cart);
                return XResp.pack(200, "加入购物车成功 ...", null);
            }
            //如果购物车中已经存在该书籍则数量加1
            cartOne.setNum(cartOne.getNum()+1);
            cartService.updateCart(cartOne);
            return XResp.pack(200, "加入购物车成功 ...", null);
        }
        return XResp.pack(401, "加入购物车失败 ...角色不是买家", null);
    }

    @GetMapping("/userc/cart/findAll")//查找当前登录用户购物车中的所有书籍
    public XJson findAll() {
        Object who = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) who;
        int uid = user.getId();
        List<Cart> allCartByUid = cartService.findAllCartByUid(uid);
        return XResp.pack(200, "", allCartByUid);
    }

    @PostMapping("/userc/cart/update")
    public XJson updateCart(Cart c){
        log.info("Cart="+c);
        cartService.updateCart(c);
        return XResp.pack(200,"修改成功",null);
    }

    @GetMapping("/userc/cart/remove")
    public XJson removeHot(int id){
        log.info("id="+id);
        cartService.removeCart(id);
        return XResp.pack(200,"删除成功",null);
    }
}
