package cn.campus.platfrom.mapper;

import cn.campus.platfrom.entity.SysRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper {

    @Insert("insert into sys_role values(null,sysRole.parentId,sysRole.rolename,sysRole.createTime,sysRole.creater,sysRole.updateTime,sysRole.updater,sysRole.deleteTime,sysRole.deleter,sysRole.isDelete)")
    @Options(useGeneratedKeys = true,keyProperty = "sysRole.id")
    Long insertRole(@Param("sysRole")SysRole sysRole);

    @Select("select * from sys_role where id in (${ids})")
    List<SysRole> getSysRoleByIds(@Param("ids")String ids);

}
