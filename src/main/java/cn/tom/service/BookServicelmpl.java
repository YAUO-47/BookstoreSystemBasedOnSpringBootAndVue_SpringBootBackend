package cn.tom.service;

import cn.tom.dao.BookMapper;
import cn.tom.entity.Book;
import cn.tom.entity.BookT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@CacheConfig(cacheNames = "user")
public class BookServicelmpl implements BookService {
    @Autowired(required = false)
    BookMapper bookMapper;
    @Cacheable(value = "book")
    public List<Book> findAllBook(Map<String, Object> map){
        return bookMapper.findAllBook(map);
    };
    public int getCount(Map<String, Object> map){
        return bookMapper.getCount(map);
    };

    public int getCountByMainType(Integer typeid){
        return bookMapper.getCountByMainType(typeid);
    };
    public List<Book> findAllBookByPid(Map<String, Object> map){
        return bookMapper.findAllBookByPid(map);
    };
    public BookT findOneBook(int id){
        return bookMapper.findOneBook(id);
    };
    public int addBook(Book b){
        return bookMapper.addBook(b);
    };

    public int updateBook(Book b){
        return bookMapper.updateBook(b);
    };
    public List<Book> findAllBookByKey(Map<String, Object> map) {
        return bookMapper.findAllBookByKey(map);
    };
}
