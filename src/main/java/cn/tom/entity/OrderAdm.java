package cn.tom.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderAdm {
    private int ordid;
    private List<OrderItem> orderItem;
    private UserP user;
    private double amount;
    private int pay_status;
    private Date tpay;
    private int ship_status;
    private String shipno;
    private String shipper;
    private Date tship;
    private Date tsign;
    private Date tcreate;





    public OrderAdm() {
        this.orderItem = new ArrayList<>();
    }

    public OrderAdm(int ordid, List<OrderItem> orderItem, UserP user, double amount, int pay_status, Date tpay, int ship_status, String shipno, String shipper, Date tship, Date tsign, Date tcreate) {
        this.ordid = ordid;
        this.orderItem = orderItem;
        this.user = user;
        this.amount = amount;
        this.pay_status = pay_status;
        this.tpay = tpay;
        this.ship_status = ship_status;
        this.shipno = shipno;
        this.shipper = shipper;
        this.tship = tship;
        this.tsign = tsign;
        this.tcreate = tcreate;
    }

    @Override
    public String toString() {
        return "OrderAdm{" +
                "ordid=" + ordid +
                ", orderItem=" + orderItem +
                ", user=" + user +
                ", amount=" + amount +
                ", pay_status=" + pay_status +
                ", tpay=" + tpay +
                ", ship_status=" + ship_status +
                ", shipno='" + shipno + '\'' +
                ", shipper='" + shipper + '\'' +
                ", tship=" + tship +
                ", tsign=" + tsign +
                ", tcreate=" + tcreate +
                '}';
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

    public UserP getUser() {
        return user;
    }

    public void setUser(UserP user) {
        this.user = user;
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
}
