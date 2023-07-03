package com.zenq.cloud507b.mapper;

import com.zenq.cloud507b.po.Staff;
import com.zenq.cloud507b.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 *
 * </p>
 *
 * @author chenxi
 * @since 2023-07-03
 */
@Mapper
public interface LoginMapper {
	@Select("select * from user where username=#{name}")
	public User getpwdbyname(@Param("name") String name);
	@Select("select * from user where userid=#{id}")
	public User getnamebyid(@Param("id") String id);
}
