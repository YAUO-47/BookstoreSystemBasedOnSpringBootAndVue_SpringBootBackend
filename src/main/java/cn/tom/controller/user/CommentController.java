package cn.tom.controller.user;


import cn.tom.entity.Comment;
import cn.tom.entity.Type;
import cn.tom.entity.XJson;
import cn.tom.entity.XResp;
import cn.tom.service.CartService;
import cn.tom.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/userc/comment/findAll")
    public XJson findAll(int bookid){
        List<Comment> lst = commentService.findAllComment(bookid);
        return XResp.pack(200,"",lst);
    }


    @PostMapping("/userc/comment/add")//添加评论
    public XJson addComment(Comment comment){
        Date now = new Date();
        comment.setTpost(now);
        commentService.addComment(comment);
        return XResp.pack(200,"添加成功",null);
    }
}
