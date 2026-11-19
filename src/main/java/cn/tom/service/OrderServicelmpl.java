package cn.tom.service;

import cn.tom.dao.OrderMapper;
import cn.tom.entity.Order;
import cn.tom.entity.OrderAdm;
import cn.tom.entity.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServicelmpl implements OrderService{
    @Autowired(required = false)
    OrderMapper orderMapper;
    public int addPay(@Param("ordid") int ordid, @Param("tpay") Date tpay){
        return orderMapper.addPay(ordid,tpay);
    };
    public int doSign(@Param("ordid") int ordid,@Param("tsign") Date tsign){
       return orderMapper.doSign(ordid,tsign);
    };
    public int addOrder(Order order){
        return orderMapper.addOrder(order);
    };
    public int findAddOrdid(){
        return orderMapper.findAddOrdid();
    };
    public List<OrderAdm> findAllOrderAdm(Map<String, Object> map){return orderMapper.findAllOrderAdm(map);};
    public int addOrderItem(OrderItem orderItem){
        return orderMapper.addOrderItem(orderItem);
    };
    public Order findOne(int ordid){
        return orderMapper.findOne(ordid);
    };
    public List<Order> findAllOrder(Map<String, Object> map){
        return orderMapper.findAllOrder(map);
    };
    public int updateShip(@Param("ordid") int ordid,@Param("shipno") String shipno,@Param("shipper")String shipper,@Param("tship") Date tship){
        return orderMapper.updateShip(ordid,shipno,shipper,tship);
    };
    public int getCount(Map<String, Object> map){
        return orderMapper.getCount(map);
    };
    public int getCountAdm(Map<String, Object> map){
        return orderMapper.getCountAdm(map);
    };
}
