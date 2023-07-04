package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Help;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HelpMapper {
    @Insert("insert into help (userid,content,time) values (#{userid},#{content},#{time})")
    public Integer insertHelpList(@Param("userid")String userid, @Param("content")String content, @Param("time")String time);

    @Select("select usermail from user where userid=(#{userid})")
    public String selectUsermailList(@Param("userid") String userid);

    @Select("select * from help")
    public List<Help> selectHelpList();
}
