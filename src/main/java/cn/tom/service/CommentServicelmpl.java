package cn.tom.service;

import cn.tom.dao.CommentMapper;
import cn.tom.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServicelmpl implements CommentService {
    @Autowired(required = false)
    CommentMapper commentMapper;
    public List<Comment> findAllComment(int bookid){
        return commentMapper.findAllComment(bookid);
    };
    public int addComment(Comment comment){
        return commentMapper.addComment(comment);
    };
}
