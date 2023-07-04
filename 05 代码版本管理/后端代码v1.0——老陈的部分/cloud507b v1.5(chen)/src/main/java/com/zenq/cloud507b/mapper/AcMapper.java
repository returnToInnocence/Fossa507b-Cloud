package com.zenq.cloud507b.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AcMapper {


    @Update("update acTemExp set ExpTem=(#{intvalue}) where date=(#{date})" )
    public boolean updateAcExpTem(@Param("intvalue")Integer intvalue,
                              @Param("date")String date);
    @Select("select temper from ac")
    public List<Integer> selectListAcTemper();

    @Select("select population from ac")
    public List<Integer> selectListPopulation();
    @Select("select population from ac where temper=(#{temper})")
    public Integer selectPopulationList(@Param("temper") Integer temper);

    @Update("update ac set population = (#{population}) where temper=(#{temper})")
    public Integer updatePopulationList(@Param("population") Integer population, @Param("temper") Integer temper);

    @Insert("insert into ac (temper,population) values (#{temper},#{population})")
    public Integer insertAcList(@Param("temper")Integer temper,
                                @Param("population")Integer population);

    @Select("select date from acTemExp where date=(#{date})")
    public String selectDatebyAcTemExp(@Param("date") String date);
    @Update("update acTemExp set value=(#{temper}) where date=(#{date})")
    public boolean setTodayAcTem(@Param("date")String today,
                                 @Param("temper")Integer temper);
    @Insert("insert into acTemExp(date,value) values (#{today},#{temper})")
    public boolean addTodayAcTem(@Param("today") String date,
                                 @Param("temper") Integer value);
}
