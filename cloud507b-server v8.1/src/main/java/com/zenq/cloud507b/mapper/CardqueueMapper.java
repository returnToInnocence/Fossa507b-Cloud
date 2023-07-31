package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Cardqueue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CardqueueMapper {
    @Select("select * from cardqueue")
    public List<Cardqueue> selectCardqueueList();

    @Select("select date from cardqueue where userid = (#{userid})")
    public String selectCardqueueDate(@Param("userid")String userid);

    @Select("select count(userid) from cardqueue")
    public int selectCardqueueCountList();

    @Insert("insert into cardqueue (userid, date) values (#{userid}, #{date})")
    public int insertCardqueueList(@Param("userid")String userid,
                                   @Param("date")String date);

    @Delete("delete from cardqueue")
    public int deleteCardqueueList();

    @Select("select teachermail from respteacher where respstatus=1")
    public String selectCardRespemailList();

}
