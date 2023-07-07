package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Noice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NoiceMapper {


    @Select("select noice_status0_value from noice")
    public int selectNoice0List();

    @Select("select noice_status1_value from noice")
    public int selectNoice1List();

    @Select("select noice_status2_value from noice")
    public int selectNoice2List();

    @Select("select noice_status3_value from noice")
    public int selectNoice3List();

    @Update("update noice set noice_status0_value = #{noice_status0_value}")
    public int updatetNoice0List(@Param("noice_status0_value")String noice_status0_value);

    @Update("update noice set noice_status1_value = #{noice_status1_value}")
    public int updatetNoice1List(@Param("noice_status1_value")String noice_status1_value);

    @Update("update noice set noice_status2_value = #{noice_status2_value}")
    public int updatetNoice2List(@Param("noice_status2_value")String noice_status2_value);

    @Update("update noice set noice_status3_value = #{noice_status3_value}")
    public int updatetNoice3List(@Param("noice_status3_value")String noice_status3_value);

    @Update("update noice set date = #{date}")
    public int updatetNoicedateList(@Param("date")String date);

    @Select("select date from noice where date=(#{date})")
    public String selectDatebyNoice(String date);

    @Select("select * from noice")
    public List<Noice> selectNoiceList();
    @Select("select * from noice where date=(#{date})")
    public List<Noice> selectTodayNoiceList(@Param("date") String date);
}
