package cn.campus.platfrom.mapper;

import cn.campus.platfrom.entity.SysUser;
import cn.campus.platfrom.sql.SysUserSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserMapper {

    @Insert("insert into sys_user values(null,#{sysUser.userName},#{sysUser.password},#{sysUser.salt},#{sysUser.errorCount},#{sysUser.isLock},#{sysUser.phone},#{sysUser.qq},#{sysUser.sex},#{sysUser.email},#{sysUser.lastLoginTime},#{sysUser.createTime},#{sysUser.creater},#{sysUser.updateTime},#{sysUser.updater},#{sysUser.deleteTime},#{sysUser.deleter},#{sysUser.isDelete})")
    @Options(useGeneratedKeys = true,keyProperty = "sysUser.id")
    Long insert(@Param("sysUser")SysUser sysUser);

    @Update("update sys_user set error_count=error_count+1 where user_name=#{userName}")
    Long addErrorCount(@Param("userName")String userName);

    @Select("select * from sys_user where id = #{id}")
    SysUser getById(@Param("id")Long id);

    @SelectProvider(type = SysUserSqlProvider.class,method = "getSysUser")
    List<SysUser> getSysUser(@Param("sysUser")SysUser sysUser);

    @Select("select * from sys_user where user_name = #{userName} and is_delete=0")
    List<SysUser> getByUsername(@Param("userName")String userName);

}
