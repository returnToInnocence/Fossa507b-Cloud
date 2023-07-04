package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface seatMapper {
    @Select("select * from seat")
    public List<seat> selectListSeat();
}
