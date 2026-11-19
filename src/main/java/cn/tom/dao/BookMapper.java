package cn.tom.dao;

import cn.tom.entity.Book;
import cn.tom.entity.BookT;
import cn.tom.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {
    public List<Book> findAllBook(Map<String, Object> map);
    public List<Book> findAllBookByKey(Map<String, Object> map);
    public List<Book> findAllBookByPid(Map<String, Object> map);
    public BookT findOneBook(int id);
    public int getCount(Map<String, Object> map);
    public int getCountByMainType(Integer pid);
    public int addBook(Book b);
    public int updateBook(Book b);
}
