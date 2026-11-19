package cn.tom.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart {
    private Integer id;
    private Integer uid;  ////购物车转订单, 前端无需上传
    private BookT book;    //内嵌书本对象，购物车转订单 前端需要给 book.id 值就行， 其他留空
    private Integer num;
    private Double price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tcreate;   //购物车转订单, 前端无需上传

    public Cart() {
    }

    public Cart(Integer id, Integer uid, BookT book, Integer num, Double price, Date tcreate) {
        this.id = id;
        this.uid = uid;
        this.book = book;
        this.num = num;
        this.price = price;
        this.tcreate = tcreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BookT getBook() {
        return book;
    }

    public void setBook(BookT book) {
        this.book = book;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getTcreate() {
        return tcreate;
    }

    public void setTcreate(Date tcreate) {
        this.tcreate = tcreate;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", uid=" + uid +
                ", book=" + book +
                ", num=" + num +
                ", price=" + price +
                ", tcreate=" + tcreate +
                '}';
    }
}
