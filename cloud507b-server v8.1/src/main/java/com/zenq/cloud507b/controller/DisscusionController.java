package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.config.common.ErrorMessage;
import com.zenq.cloud507b.config.common.SuccessMessage;
import com.zenq.cloud507b.po.Disscusion;
import com.zenq.cloud507b.service.DisscusionService;
import com.zenq.cloud507b.service.TweetService;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 *  需求：评论帖子，点赞帖子，取消点赞
 * </p>
 *
 * @author chenxi
 * @since 2023-07-01
 */
@CrossOrigin(origins = "*")
@RestController
public class DisscusionController {
    @Autowired
    DisscusionService disscusionService;
    @Autowired
    TweetService tweetService;

    @GetMapping("/api/getdisscusionList")
    public Object getTweetDisscusionList(HttpServletRequest req){
        try {
            return new SuccessMessage<List<Disscusion>>("获取评论成功",disscusionService.disscusionList()).getMessage();
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("获取评论失败").getMessage();
        }
    }

    @PostMapping("/api/CreateDisscusion")
    public Object publishDisscusion(@RequestBody Map<String, Object> requestData){
        //向数据库添加数据，添加评论
        try {
            String tweetid = (String) requestData.get("id");
            int count = disscusionService.selectCount(tweetid);
            String id = String.valueOf(count++);
            String observer = (String) requestData.get("observer");
            String comment = (String) requestData.get("content");
            String time = (String) requestData.get("time");
            System.out.println(id);
            System.out.println(tweetid);
            System.out.println(observer);
            System.out.println(comment);
            System.out.println(time);
            Disscusion disscusion = new Disscusion(id, tweetid, observer, comment, time);
            if(id == null || tweetid == null || observer == null || comment == null){
                return new ErrorMessage("参数缺失").getMessage();
            }else{
                return new SuccessMessage<>("发表评论成功", disscusionService.publishdisscusion(disscusion)).getMessage();
            }
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("发表评论失败").getMessage();
        }
    }

    @PostMapping("/api/DeleteDisscusion")
    public Object DeleteDisscusion(HttpServletRequest req){
        //从数据库删除数据，删除评论
        try {
                String id = req.getParameter("id");
                return new SuccessMessage<>("删除评论成功", disscusionService.deletedisscusion(id)).getMessage();
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("删除评论失败").getMessage();
        }
    }

    @PostMapping("/api/addfollower")
    public Object addFollow(HttpServletRequest req){
        //向数据库添加数据，点赞帖子
        try {
            String id = req.getParameter("id");
            String followers = req.getParameter("followers");
            String follower = tweetService.tweetFollower(id);
            System.out.println("获取到的点赞人为："+follower);
            if(follower == null){
                System.out.println("走createfollowTweet");
                return new SuccessMessage<Boolean>("点赞成功",tweetService.createfollowTweet(id, followers));
            }
            else{
                System.out.println("走addfollowTweet");
                return new SuccessMessage<Boolean>("点赞成功",tweetService.addfollowTweet(id, followers));
            }
        }catch (Exception e){
            System.out.println(e);
            return new ErrorMessage("点赞失败").getMessage();
        }
    }

//    @PostMapping("/api/delfollower")
//    @ResponseBody
//    public Object delFollow(HttpServletRequest req){
//        //从数据库删除数据，取消点赞
//        try {
//            String id = req.getParameter("id");
//            String followers = req.getParameter("followers");
//            return new SuccessMessage<Boolean>("点赞成功",tweetService.followTweet(id, followers));
//        }catch (Exception e){
//            System.out.println(e);
//            return new ErrorMessage("点赞失败").getMessage();
//        }
//    }
}
