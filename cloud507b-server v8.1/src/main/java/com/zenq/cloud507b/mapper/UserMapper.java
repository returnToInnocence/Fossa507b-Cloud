package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenxi
 * @since 2023-07-01
 */
@Mapper
public interface UserMapper {
    @Select("select * from user")
    public List<User> selectUserList();
    @Select("select username from user where username=#{username}")
    public String selectUserName(@Param("username") String username);

    @Select("select userid from user where userid=#{userid}")
    public String selectUserID(@Param("userid")String userid);

    @Select("select username from user where userid=#{userid}")
    public String selectUserNameByID(@Param("userid")String userid);

    @Insert("insert into user(userid,username,usermail,userpsd,userstatus,usergroup) " +
            "values(#{userid}, #{username}, #{usermail}, #{userpsd}, #{userstatus}, #{usergroup})")
    public boolean addUser(User user);

    @Update("update user SET " +
//            "userid = #{userid}, username = #{username}," +
            "usermail = #{usermail}, userpsd = #{userpsd}," +
            "userstatus = #{userstatus}, usergroup = #{usergroup}" +
            "WHERE userid = #{userid} AND username = #{username}")
    public boolean updateUser(User user);

    @Select("select usermail from user where userid = #{userid}")
    public String selectUsermail(String userid);


}
