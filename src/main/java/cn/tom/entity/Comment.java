package cn.tom.entity;

import java.util.Date;

public class Comment {
    private int id;
    private int uid;
    private int bookid;
    private String content;
    private String nickname;
    private int rate;
    private String img;
    private Date tpost;

    public Comment(int id, int uid, int bookid, String content, String nickname, int rate, String img, Date tpost) {
        this.id = id;
        this.uid = uid;
        this.bookid = bookid;
        this.content = content;
        this.nickname = nickname;
        this.rate = rate;
        this.img = img;
        this.tpost = tpost;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", uid=" + uid +
                ", bookid=" + bookid +
                ", content='" + content + '\'' +
                ", nickname='" + nickname + '\'' +
                ", rate=" + rate +
                ", img='" + img + '\'' +
                ", tpost=" + tpost +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getTpost() {
        return tpost;
    }

    public void setTpost(Date tpost) {
        this.tpost = tpost;
    }
}
