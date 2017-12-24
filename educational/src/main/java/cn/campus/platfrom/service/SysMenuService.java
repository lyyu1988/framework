package cn.campus.platfrom.service;

import cn.campus.platfrom.entity.SysMenu;

import java.util.Collection;
import java.util.List;

public interface SysMenuService {

    List<SysMenu> find(SysMenu sysMenu, Long roleId);

    List<SysMenu> find(Long roleId);

    List<SysMenu> find(Collection<Long> roleIds);

}
