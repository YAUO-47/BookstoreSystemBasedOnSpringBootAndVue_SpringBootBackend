package cn.tom.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private int id;
    private String nickname;  //姓名
    private String account;   //登录账号
    private String password;   //密码
    private String role;       //角色 adm 后台卖家   usr=买家
    private String phone;      //电话
    private int vip;        //0 普通买家， 1=会员
    private String addr;       //地址
    private String img = "";     //图片ID


    public User() {
        int id=0;
      String nickname="";  //姓名
       String account="";   //登录账号
         String password="";   //密码
        String role="";       //角色 adm 后台卖家   usr=买家
         String phone="";      //电话
         int vip=0;        //0 普通买家， 1=会员
         String addr="";       //地址
        String img = "";
    }

    public User(String account, String role, int id, String img) {
        this.id = id;
        this.account = account;
        this.role = role;
        this.img = img;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> lst = new ArrayList<>();
        lst.add(new SimpleGrantedAuthority(this.role));
        return lst;
    }

    @Override
    public String getUsername() {
        return this.account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", vip=" + vip +
                ", addr='" + addr + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
