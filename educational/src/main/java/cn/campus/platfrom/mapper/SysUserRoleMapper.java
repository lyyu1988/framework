package cn.campus.platfrom.mapper;

import cn.campus.platfrom.entity.SysUserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    @Select("select user_id from sys_user_role where user_id=#{userId}")
    List<Long> findRoleIds(@Param("userId") Long userId);
}
