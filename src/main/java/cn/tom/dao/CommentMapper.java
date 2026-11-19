package cn.tom.dao;

import cn.tom.entity.Comment;
import cn.tom.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CommentMapper {
    public List<Comment> findAllComment(int bookid);
    public int addComment(Comment comment);


}
