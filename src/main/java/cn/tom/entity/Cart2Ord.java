package cn.tom.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart2Ord {
    private Integer ordid;  //前端无需上传
    private Integer uid;    //前端无需上传
    private Double amount;      //前端无需上传  订单总价
    private String ship2name;    //收货金额
    private String ship2phone;   //收获人电话
    private String ship2addr;    //发货地址
    private String shipper;     //前端无需上传  承运人
    List<Cart> carts = new ArrayList<>();

    public Cart2Ord() {
    }

    public Cart2Ord(Integer ordid, Integer uid, Double amount, String ship2name, String ship2phone, String ship2addr, String shipper, List<Cart> carts) {
        this.ordid = ordid;
        this.uid = uid;
        this.amount = amount;
        this.ship2name = ship2name;
        this.ship2phone = ship2phone;
        this.ship2addr = ship2addr;
        this.shipper = shipper;
        this.carts = carts;
    }

    public Integer getOrdid() {
        return ordid;
    }

    public void setOrdid(Integer ordid) {
        this.ordid = ordid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getShip2name() {
        return ship2name;
    }

    public void setShip2name(String ship2name) {
        this.ship2name = ship2name;
    }

    public String getShip2phone() {
        return ship2phone;
    }

    public void setShip2phone(String ship2phone) {
        this.ship2phone = ship2phone;
    }

    public String getShip2addr() {
        return ship2addr;
    }

    public void setShip2addr(String ship2addr) {
        this.ship2addr = ship2addr;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Cart2Ord{" +
                "ordid=" + ordid +
                ", uid=" + uid +
                ", amount=" + amount +
                ", ship2name='" + ship2name + '\'' +
                ", ship2phone='" + ship2phone + '\'' +
                ", ship2addr='" + ship2addr + '\'' +
                ", shipper='" + shipper + '\'' +
                ", carts=" + carts +
                '}';
    }
}
