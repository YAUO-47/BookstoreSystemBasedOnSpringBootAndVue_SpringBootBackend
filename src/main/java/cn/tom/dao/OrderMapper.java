package cn.tom.dao;

import cn.tom.entity.Order;
import cn.tom.entity.OrderAdm;
import cn.tom.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    public int addPay(@Param("ordid") int ordid,@Param("tpay") Date tpay);
    public int doSign(@Param("ordid") int ordid,@Param("tsign") Date tsign);
    public int updateShip(@Param("ordid") int ordid,@Param("shipno") String shipno,@Param("shipper")String shipper,@Param("tship") Date tship);
    public int addOrder(Order order);
    public int addOrderItem(OrderItem orderItem);
    public int findAddOrdid();
    public int getCount(Map<String, Object> map);
    public int getCountAdm(Map<String, Object> map);
    public Order findOne(int ordid);
    public List<Order> findAllOrder(Map<String, Object> map);
    public List<OrderAdm> findAllOrderAdm(Map<String, Object> map);
    public List<Order> getOrderItemsByOrderId(int orderId);
}
