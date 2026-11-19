package cn.tom.service;

import cn.tom.dao.CartMapper;
import cn.tom.entity.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServicelmpl implements CartService{
    @Autowired(required = false)
    CartMapper cartMapper;
    public List<Cart> findAllCartByUid(int uid){
       return cartMapper.findAllCartByUid(uid);
    };
    public int addCart(Cart c){
        return cartMapper.addCart(c);
    };
    public int updateCart(Cart c){return cartMapper.updateCart(c);};
    public Cart findCartOne(@Param("bookid") int bookid, @Param("uid")int uid){
        return cartMapper.findCartOne(bookid,uid);
    };

    public int removeCart(int id){return cartMapper.removeCart(id);};
}
