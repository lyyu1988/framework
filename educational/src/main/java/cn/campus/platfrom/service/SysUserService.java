package cn.campus.platfrom.service;

import cn.campus.platfrom.entity.SysUser;

import java.util.List;

public interface SysUserService {

    SysUser insert(SysUser sysUser);

    Long addErrorCount(String userName);

    SysUser getById(Long id);

    List<SysUser> list(SysUser sysUser);

    List<SysUser> getByUsername(String userName);
}
