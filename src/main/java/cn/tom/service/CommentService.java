package cn.tom.service;

import cn.tom.entity.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> findAllComment(int bookid);
    public int addComment(Comment comment);
}
