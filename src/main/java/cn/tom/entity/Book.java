package cn.tom.entity;

import java.util.Date;

public class Book {
    private int id;
    private String name;
    private String sn;
    private double price;
    private double price2;
    private String maker;
    private Date tmake;
    private int typeid;
    private String pic;
    private String author;
    private int salenum;
    private int stock;
    private String info;
    private String info2;

    public Book(int id, String name, String sn, double price, double price2, String maker, Date tmake, int typeid, String pic, String author, int salenum, int stock, String info, String info2) {
        this.id = id;
        this.name = name;
        this.sn = sn;
        this.price = price;
        this.price2 = price2;
        this.maker = maker;
        this.tmake = tmake;
        this.typeid = typeid;
        this.pic = pic;
        this.author = author;
        this.salenum = salenum;
        this.stock = stock;
        this.info = info;
        this.info2 = info2;
    }

    public Book() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Date getTmake() {
        return tmake;
    }

    public void setTmake(Date tmake) {
        this.tmake = tmake;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getSalenum() {
        return salenum;
    }

    public void setSalenum(int salenum) {
        this.salenum = salenum;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }



    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                ", price=" + price +
                ", price2=" + price2 +
                ", maker='" + maker + '\'' +
                ", tmake=" + tmake +
                ", typeid=" + typeid +
                ", pic='" + pic + '\'' +
                ", author='" + author + '\'' +
                ", salenum=" + salenum +
                ", stock=" + stock +
                ", info='" + info + '\'' +
                ", info2='" + info2 + '\'' +
                '}';
    }
}
