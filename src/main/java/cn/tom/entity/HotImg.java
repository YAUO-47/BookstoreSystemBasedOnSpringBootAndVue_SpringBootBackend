package cn.tom.entity;

public class HotImg {
    private int id;
    private String memo;    //信息
    private String picture;  //图片
    private int hot;   //热度排序
    private int type;  //类别

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public HotImg() {
    }

    @Override
    public String toString() {
        return "HotImg{" +
                "id=" + id +
                ", memo='" + memo + '\'' +
                ", picture='" + picture + '\'' +
                ", hot=" + hot +
                ", type=" + type +
                '}';
    }

    public HotImg(int id, String memo, String picture, int hot, int type) {
        this.id = id;
        this.memo = memo;
        this.picture = picture;
        this.hot = hot;
        this.type = type;
    }
}
