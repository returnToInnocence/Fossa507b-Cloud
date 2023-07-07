package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SeatMapper {
    @Select("select * from seat")
    public List<Seat> selectListSeat();

    @Select("select seatstatus from seat")
    public List<Integer> selectListStatus();

    @Select("select seatstatus from seat where seatid = #{seatid}")
    public String selectStatusbyID(@Param("seatid") String seatid);

    @Select("select seatuserguest from seat where seatid = #{seatid}")
    public String selectGuestbyID(@Param("seatid") String seatid);

    @Update("update seat set seatstatus = #{status}, seatuserguest = #{guest} where seatid = #{id}" )
    public int updateSeat(@Param("status") String status, @Param("guest") String guest, @Param("id") String id);

    @Update("update seatUsedTime set value = #{value} where date = #{date}")
    public Object setSeatUsedValue(@Param("value") Integer value, @Param("date") String date);
}
