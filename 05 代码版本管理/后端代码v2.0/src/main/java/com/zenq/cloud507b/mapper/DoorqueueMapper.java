package com.zenq.cloud507b.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DoorqueueMapper {
    @Insert("insert into doorqueue (userid) values (#{userid})")
    public int insertDoorqueueList(@Param("userid")String userid);

    @Delete("delete from doorqueue where userid=(#{userid})")
    public int deleteDoorqueueList(@Param("userid")String userid);
}
