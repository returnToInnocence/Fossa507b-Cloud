package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SeatsMapper {

    @Select("select * from `seat`")
    public List<Seat> selectAll();
}
