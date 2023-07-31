package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Doorqueue;
import com.zenq.cloud507b.po.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DoorqueueMapper {
    @Insert("insert into doorqueue (userid) values (#{userid})")
    public int insertDoorqueueList(@Param("userid")String userid);

    @Delete("delete from doorqueue where userid=(#{userid})")
    public int deleteDoorqueueList(@Param("userid")String userid);

    @Select("select * from doorqueue")
    public List<Doorqueue> selectDoorList();
}
