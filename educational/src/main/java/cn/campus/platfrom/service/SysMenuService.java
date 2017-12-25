package cn.campus.platfrom.service;

import cn.campus.platfrom.entity.SysMenu;
import cn.campus.platfrom.util.MyPage;

import java.util.Collection;
import java.util.List;

public interface SysMenuService {

    MyPage<SysMenu> find(MyPage<SysMenu> page, SysMenu sysMenu, Long roleId);

    List<SysMenu> find(SysMenu sysMenu,Long roleId);

    List<SysMenu> find(Collection<Long> roleIds);

    SysMenu find(Long id);

    SysMenu insert(SysMenu sysMenu);

    List<SysMenu> findTree(Long parentId);

    List<SysMenu> findByRoleId(Long roleId);

}
