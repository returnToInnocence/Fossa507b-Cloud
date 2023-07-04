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
    @Select("select * from disscusion")
    public List<Disscusion> selectDisscusionList();

    @Insert("insert into disscusion(id, tweet_id, observer, comment) values (#{id}, #{tweetId}, #{observer}, #{comment})")
    public int insertTweetDisscusion(Disscusion disscusion);

    @Delete("delete from disscusion where id=#{id}")
    public int deleteTweetDisscusion(@Param("id")String id);
}
