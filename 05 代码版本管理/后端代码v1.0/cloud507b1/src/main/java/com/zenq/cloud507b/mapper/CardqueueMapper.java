package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Cardqueue;
import com.zenq.cloud507b.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CardqueueMapper {
    @Select("select * from cardqueue")
    public List<Cardqueue> selectCardqueueList();

    @Select("select count(userid) from cardqueue")
    public int selectCardqueueCountList();

    @Insert("insert into cardqueue (userid) values (#{userid})")
    public int insertCardqueueList(@Param("userid")String userid);

    @Delete("delete from cardqueue")
    public int deleteCardqueueList();

    @Select("select teachermail from respteacher where respstatus=1")
    public String selectCardRespemailList();

}
