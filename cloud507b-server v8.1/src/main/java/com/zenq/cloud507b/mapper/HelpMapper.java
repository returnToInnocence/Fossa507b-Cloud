package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Help;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HelpMapper {
    @Insert("insert into help (username,userid,content,time) values (#{username},#{userid},#{content},#{time})")
    public Integer insertHelpList(@Param("username")String username, @Param("userid")String userid, @Param("content")String content, @Param("time")String time);

    @Select("select usermail from user where userid=(#{userid})")
    public String selectUsermailList(@Param("userid") String userid);

    @Select("select * from help")
    public List<Help> selectHelpList();

    @Delete("delete from help where content=(#{description})")
    public boolean deleteHelp(@Param("description")String description);
}
