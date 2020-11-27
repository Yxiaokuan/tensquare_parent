package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yjk
 * @description commentController
 * @date 2020/11/27
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Comment comment){
        commentService.add(comment);
        return new Result(true, StatusCode.OK,"添加成功");
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@RequestBody String  id){
        commentService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }
    @RequestMapping(value = "/article/{articleId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String articleId){
        return new Result(true, StatusCode.OK,"查询成功",commentService.findById(articleId));
    }


}
