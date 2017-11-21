package cn.campus.platfrom.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserRoleMapper {

    @Select("select user_id from sys_user_role where role_id=#{roleId}")
    List<Long> getUserByRole(@Param("roleId")Long roleId);

    @Select("select role_id from sys_user_role where user_id=#{userId}")
    List<Long> getRoleByUser(@Param("userId")Long userId);
}
