package cn.campus.platfrom.service.impl;

import cn.campus.platfrom.Constants;
import cn.campus.platfrom.entity.SysMenu;
import cn.campus.platfrom.mapper.SysMenuMapper;
import cn.campus.platfrom.service.SysMenuService;
import cn.campus.platfrom.util.LongUtils;
import cn.campus.platfrom.util.MyPage;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

@Service("sysMenuService")
@CacheConfig(cacheNames = "sysMenu")
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public MyPage<SysMenu> find(MyPage<SysMenu> page, SysMenu sysMenu, Long roleId) {
        //Wrapper<SysMenu> entityWrapper = this.getWhere(sysMenu, roleId);
        //page.setRecords(sysMenuMapper.selectPage(page,entityWrapper));
        page.setRecords(sysMenuMapper.selectWithParent(page));
        return page;
    }

    @Override
    public List<SysMenu> find(SysMenu sysMenu,Long roleId) {
        Wrapper<SysMenu> entityWrapper = this.getWhere(sysMenu, roleId);
        return sysMenuMapper.selectList(entityWrapper);
    }

    @Override
    public SysMenu find(Long id) {
        return sysMenuMapper.selectById(id);
    }

    @Override
    public List<SysMenu> find(Collection<Long> roleIds) {
        return null;
    }

    @Override
    @CachePut(key="#sysMenu.id")
    public SysMenu insert(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
        return sysMenu;
    }

    @Override
    public List<SysMenu> findTree(Long parentId) {
        return sysMenuMapper.selectByParentId(parentId);
    }

    @Override
    public List<SysMenu> findByRoleId(Long roleId) {
        return find(null,roleId);
    }

    private Wrapper<SysMenu> getWhere(SysMenu sysMenu,Long roleId){
        EntityWrapper<SysMenu> entityWrapper=new EntityWrapper<>();

        Wrapper<SysMenu> where = entityWrapper.where("is_delete={0}", Constants.NOT_DELETE);

        if(null!=sysMenu){
            if(!StringUtils.isEmpty(sysMenu.getName())){
                where.like("name","%"+sysMenu.getName()+"%");
            }
            if(!LongUtils.isNull(sysMenu.getParentId())){
                where.and("parent_id={0}",sysMenu.getParentId());
            }
        }

        where.orderBy("parent_id",true).orderBy("menu_order",true);

        return where;
    }
}
