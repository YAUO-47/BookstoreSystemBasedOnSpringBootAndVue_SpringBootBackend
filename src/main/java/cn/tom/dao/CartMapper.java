package cn.tom.dao;

import cn.tom.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
    public List<Cart> findAllCartByUid(int uid);
    public int addCart(Cart c);
    public int updateCart(Cart c);
    public Cart findCartOne(@Param("bookid") int bookid, @Param("uid")int uid);
    public int removeCart(int id);
}
