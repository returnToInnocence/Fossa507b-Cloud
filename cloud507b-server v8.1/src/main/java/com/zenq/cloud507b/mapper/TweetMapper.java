package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Tweet;
import com.zenq.cloud507b.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 *  发布帖子信息
 *  查询帖子信息
 *  删除帖子信息
 *
 * </p>
 *
 * @author chenxi
 * @since 2023-07-01
 */
@Mapper
public interface TweetMapper {
    @Select("select * from tweet")
    public List<Tweet> selectTweetList();
    @Select("select follower from tweet where id=#{id}")
    public String selectTweetFolowerByid(@Param("id")String id);
    @Update("UPDATE tweet SET follower = CONCAT(follower, #{followers}) WHERE id = #{id}")
    //原本就有点赞人，在原有基础上继续添加
    public int updateTweetFollowersById(@Param("id") String id, @Param("followers") String followers);

    @Update("UPDATE tweet SET follower = #{followers} WHERE id = #{id}")
    //原本是空的，添加一名新的点赞人
    public int insertTweetFollowersById(@Param("id") String id, @Param("followers") String followers);

    @Insert("insert into tweet(id, title, content, userid, time, img, type) " +
            "values (#{id}, #{title}, #{content}, #{userid}, #{time}, #{img}, #{type})")
    public int insertTweet(Tweet tweet);

    @Delete("delete from tweet where id=#{id}")
    public int deleteTweet(@Param("id")String id);

}
