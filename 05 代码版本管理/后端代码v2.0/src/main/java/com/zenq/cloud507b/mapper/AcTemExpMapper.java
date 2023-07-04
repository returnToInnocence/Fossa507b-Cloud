package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.AcTemExp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AcTemExpMapper {
    @Select("select * from acTemExp")
    public List<AcTemExp> selectAcTemExpList();
}
