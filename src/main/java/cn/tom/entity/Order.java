package cn.tom.entity;

import org.apache.ibatis.annotations.Options;

import java.util.Date;
import java.util.List;

public class Order {
    private int ordid;
    private List<OrderItem> orderItem;
    private int uid;
    private double amount;
    private int pay_status;
    private Date tpay;
    private int ship_status;
    private String shipno;
    private String ship2name;
    private String ship2phone;
    private String ship2addr;
    private String shipper;
    private Date tship;
    private Date tsign;
    private Date tcreate;



    public Order() {
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    public Date getTpay() {
        return tpay;
    }

    public void setTpay(Date tpay) {
        this.tpay = tpay;
    }

    public int getShip_status() {
        return ship_status;
    }

    public void setShip_status(int ship_status) {
        this.ship_status = ship_status;
    }

    public String getShipno() {
        return shipno;
    }

    public void setShipno(String shipno) {
        this.shipno = shipno;
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

    public Date getTship() {
        return tship;
    }

    public void setTship(Date tship) {
        this.tship = tship;
    }

    public Date getTsign() {
        return tsign;
    }

    public void setTsign(Date tsign) {
        this.tsign = tsign;
    }

    public Date getTcreate() {
        return tcreate;
    }

    public void setTcreate(Date tcreate) {
        this.tcreate = tcreate;
    }

    public int getOrdid() {
        return ordid;
    }

    public void setOrdid(int ordid) {
        this.ordid = ordid;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ordid=" + ordid +
                ", orderItem=" + orderItem +
                ", uid=" + uid +
                ", amount=" + amount +
                ", pay_status=" + pay_status +
                ", tpay=" + tpay +
                ", ship_status=" + ship_status +
                ", shipno='" + shipno + '\'' +
                ", ship2name='" + ship2name + '\'' +
                ", ship2phone='" + ship2phone + '\'' +
                ", ship2addr='" + ship2addr + '\'' +
                ", shipper='" + shipper + '\'' +
                ", tship=" + tship +
                ", tsign=" + tsign +
                ", tcreate=" + tcreate +
                '}';
    }

    public Order(int ordid, List<OrderItem> orderItem, int uid, double amount, int pay_status, Date tpay, int ship_status, String shipno, String ship2name, String ship2phone, String ship2addr, String shipper, Date tship, Date tsign, Date tcreate) {
        this.ordid = ordid;
        this.orderItem = orderItem;
        this.uid = uid;
        this.amount = amount;
        this.pay_status = pay_status;
        this.tpay = tpay;
        this.ship_status = ship_status;
        this.shipno = shipno;
        this.ship2name = ship2name;
        this.ship2phone = ship2phone;
        this.ship2addr = ship2addr;
        this.shipper = shipper;
        this.tship = tship;
        this.tsign = tsign;
        this.tcreate = tcreate;
    }
}
