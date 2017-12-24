package cn.campus.platfrom.service;

import cn.campus.platfrom.entity.SysRole;

import java.util.List;

public interface SysRoleService {

    List<SysRole> find(SysRole sysRole, Long userId);
    List<SysRole> find(Long userId);

    List<Long> findRoleIds(SysRole sysRole, Long userId);
    List<Long> findRoleIds(Long userId);

    SysRole inser(SysRole sysRole, List<String> menuIds);
    SysRole inser(SysRole sysRole, String menuIds);

}
