package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Disscusion;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 *
 * </p>
 *
 * @author chenxi
 * @since 2023-07-01
 */
@Mapper
public interface DisscusionMapper {
    @Select("select * from disscusion where tweetid=#{id}")
    public List<Disscusion> selectDisscusionListByid(@Param("id")String id);

    @Select("select * from disscusion")
    public List<Disscusion> selectDisscusionList();

    @Insert("insert into disscusion(id, tweetid, observer, comment, time) values (#{id}, #{tweetId}, #{observer}, #{comment}, #{time})")
    public int insertTweetDisscusion(Disscusion disscusion);

    @Delete("delete from disscusion where id=#{id}")
    public int deleteTweetDisscusion(@Param("id")String id);

    @Select("select count(id) from disscusion")
    public int selectDisscusionListCount(String tweetid);
}
