package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
