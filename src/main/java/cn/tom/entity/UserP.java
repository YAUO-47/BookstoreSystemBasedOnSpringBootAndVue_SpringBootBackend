package cn.tom.entity;

public class UserP {
    private int id;
    private String nickname;  //姓名
    private String account;   //登录账号
    private String password;   //密码
    private String role;       //角色 adm 后台卖家   usr=买家
    private String phone;      //电话
    private int vip;        //0 普通买家， 1=会员
    private String addr;       //地址
    private String img = "";     //图片ID
    private int closed;

    @Override
    public String toString() {
        return "UserP{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", vip=" + vip +
                ", addr='" + addr + '\'' +
                ", img='" + img + '\'' +
                ", closed=" + closed +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public UserP() {
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public UserP(int id, String nickname, String account, String password, String role, String phone, int vip, String addr, String img, int closed) {
        this.id = id;
        this.nickname = nickname;
        this.account = account;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.vip = vip;
        this.addr = addr;
        this.img = img;
        this.closed = closed;
    }
}
