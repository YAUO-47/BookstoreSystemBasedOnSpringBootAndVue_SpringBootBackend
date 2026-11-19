package cn.tom.controller.user;


import cn.tom.entity.*;
import cn.tom.service.BookService;
import cn.tom.service.CartService;
import cn.tom.service.OrderService;
import cn.tom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {
    Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @GetMapping("/userc/order/findAll")
    public XJson findAll(Integer start, Integer pageline,Integer shipStatus, Integer payStatus,
    Integer days) {
        Object who = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) who;
        int uid = user.getId();
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("pageline", pageline);
        map.put("uid", uid);
        map.put("shipStatus",shipStatus);
        map.put("payStatus",payStatus);
        map.put("days",days);
        List<Order> lst = orderService.findAllOrder(map);
        int count = orderService.getCount(map);
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("lst", lst);
        returnMap.put("count", count);
        return XResp.pack(200, "", returnMap);
    }

    @GetMapping("/adm/order/findAllAdm")
    public XJson findAllAdm(Integer start, Integer pageline,Integer shipStatus, Integer payStatus,
                         Integer days,String nickname,String account,String phone) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("pageline", pageline);
        map.put("phone", phone);
        map.put("account", account);
        map.put("nickname", nickname);
        map.put("shipStatus",shipStatus);
        map.put("payStatus",payStatus);
        map.put("days",days);
        List<OrderAdm> lst = orderService.findAllOrderAdm(map);
        int count = orderService.getCountAdm(map);
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("lst", lst);
        returnMap.put("count", count);
        return XResp.pack(200, "", returnMap);
    }

    @GetMapping("/userc/order/findOne")
    public XJson findOne(Integer ordid) {
        Order order = orderService.findOne(ordid);
        return XResp.pack(200, "", order);
    }

    @PostMapping("/adm/order/updateShip/{ordid}")//发货
    public XJson updateShip(@PathVariable("ordid") Integer ordid,@RequestBody Map<String, Object> fd) {
        String shipno = (String) fd.get("shipno");
        String shipper = (String) fd.get("shipper");
        if (shipno!= null && shipno != "") {
            orderService.updateShip(ordid, shipno, shipper, new Date());
        }
        return XResp.pack(200, "", null);
    }

    @PostMapping("/userc/order/payOrder/{ordid}")
    public XJson payOrder(@PathVariable("ordid") Integer ordid) {//付款
        orderService.addPay(ordid,new Date());
        Order one = orderService.findOne(ordid);
        List<OrderItem> orderItem = one.getOrderItem();
        for(int i=0;i<orderItem.size();i++){
            Book book = new Book();
            book.setId(orderItem.get(i).getBook().getId());
            book.setSalenum(orderItem.get(i).getBook().getSalenum()+orderItem.get(i).getNum());
            book.setStock(orderItem.get(i).getBook().getStock()-orderItem.get(i).getNum());
            bookService.updateBook(book);
        }
        return XResp.pack(200, "", null);
    }


    @PostMapping("/userc/order/doSign/{ordid}")//确认收货
    public XJson doSign(@PathVariable("ordid") Integer ordid) {
        orderService.doSign(ordid,new Date());
        return XResp.pack(200, "", null);
    }

    @PostMapping("/userc/order/createOrder")

    public XJson createOrder(@RequestBody Cart2Ord carts) {//购物车转订单
        Object who = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) who;
        int id = user.getId();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        Order newOrder  = new Order();
        newOrder.setUid(id);
        double totalPrice = 0;
        for(int i=0;i<carts.getCarts().size();i++){
            totalPrice+=carts.getCarts().get(i).getNum()*carts.getCarts().get(i).getPrice();
        }
        newOrder.setAmount(totalPrice);
        newOrder.setPay_status(0);
        newOrder.setTpay(null);
        newOrder.setShip_status(0);
        newOrder.setShipno("");
        newOrder.setShip2name(carts.getShip2name());
        newOrder.setShip2phone(carts.getShip2phone());
        newOrder.setShip2addr(carts.getShip2addr());
        newOrder.setShipper(null);
        newOrder.setTship(null);
        newOrder.setTsign(null);
        newOrder.setTcreate(new Date());
        orderService.addOrder(newOrder);
        int ordid =orderService.findAddOrdid();
        for(int i=0;i<carts.getCarts().size();i++){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(carts.getCarts().get(i).getBook());
            System.out.println(carts.getCarts().get(i).getBook());
            orderItem.setNum(carts.getCarts().get(i).getNum());
            orderItem.setPrice(carts.getCarts().get(i).getPrice());
            orderItem.setOrdid(ordid);
            orderService.addOrderItem(orderItem);
        }
        return XResp.pack(200, "", ordid);
    }
}
