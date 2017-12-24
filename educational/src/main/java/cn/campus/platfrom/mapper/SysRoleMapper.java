package cn.campus.platfrom.mapper;

import cn.campus.platfrom.entity.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    /*
    @Insert("insert into sys_role values(null,sysRole.parentId,sysRole.rolename,sysRole.createTime,sysRole.creater,sysRole.updateTime,sysRole.updater,sysRole.deleteTime,sysRole.deleter,sysRole.isDelete)")
    @Options(useGeneratedKeys = true,keyProperty = "sysRole.id")
    Long insertRole(@Param("sysRole")SysRole sysRole);

    @Select("select * from sys_role where id in (${ids})")
    List<SysRole> getSysRoleByIds(@Param("ids")String ids);
    */

}
