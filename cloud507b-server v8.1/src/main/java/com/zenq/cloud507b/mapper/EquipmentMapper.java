package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EquipmentMapper {
    @Insert("insert into equipment (userid,text,time,status) values (#{userid},#{text},#{time},#{status})")
    public int insertEquipmentList(@Param("userid")String userid, @Param("text")String text, @Param("time")String time, @Param("status")String status);

    @Select("select * from equipment")
    public List<Equipment> selectEquipmentList();

    @Select("select count(userid) from equipment")
    public int selectEquipmentCountList();

    @Select("select teachermail from respteacher where respstatus=1")
    public String selectRespemailList();

    @Delete("delete from equipment where userid=(#{userid})")
    public int deleteEquipmentList(@Param("userid") String userid);
}
