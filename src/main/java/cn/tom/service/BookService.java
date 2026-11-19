package cn.tom.service;

import cn.tom.entity.Book;
import cn.tom.entity.BookT;

import java.util.List;
import java.util.Map;

public interface BookService {
    public List<Book> findAllBook(Map<String, Object> map);
    public List<Book> findAllBookByKey(Map<String, Object> map);
    public List<Book> findAllBookByPid(Map<String, Object> map);
    public BookT findOneBook(int id);
    public int getCount(Map<String, Object> map);
    public int getCountByMainType(Integer pid);
    public int addBook(Book b);
    public int updateBook(Book b);
}
