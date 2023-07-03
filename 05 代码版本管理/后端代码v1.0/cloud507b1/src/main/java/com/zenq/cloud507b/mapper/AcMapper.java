package com.zenq.cloud507b.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface AcMapper {
    @Select("select population from ac where temper=(#{temper})")
    public Integer selectPopulationList(@Param("temper") Integer temper);

    @Update("update ac set population = (#{population}) where temper=(#{temper})")
    public Integer updatePopulationList(@Param("population") Integer population, @Param("temper") Integer temper);

    @Insert("insert into ac (temper,population) values (#{temper},#{population})")
    public Integer insertAcList(@Param("temper")Integer temper, @Param("population")Integer population);
}
