package cn.tom.entity;

public class OrderItem {
    private int itemid;
    private int ordid;
    private BookT book;
    private int num;
    private double price;


    public OrderItem() {
    }


    public OrderItem(int itemid, int ordid, BookT book, int num, double price) {
        this.itemid = itemid;
        this.ordid = ordid;
        this.book = book;
        this.num = num;
        this.price = price;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }



    public BookT getBook() {
        return book;
    }

    public void setBook(BookT book) {
        this.book = book;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrdid() {
        return ordid;
    }

    public void setOrdid(int ordid) {
        this.ordid = ordid;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemid=" + itemid +
                ", ordid=" + ordid +
                ", book=" + book +
                ", num=" + num +
                ", price=" + price +
                '}';
    }
}
