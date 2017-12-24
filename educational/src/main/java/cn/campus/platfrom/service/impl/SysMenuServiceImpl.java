package cn.campus.platfrom.service.impl;

import cn.campus.platfrom.Constants;
import cn.campus.platfrom.entity.SysMenu;
import cn.campus.platfrom.mapper.SysMenuMapper;
import cn.campus.platfrom.service.SysMenuService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> find(SysMenu sysMenu,Long roleId) {

        EntityWrapper<SysMenu> entityWrapper=new EntityWrapper<>();

        Wrapper<SysMenu> where = entityWrapper.where("is_delete={0}", Constants.NOT_DELETE);

        if(null!=sysMenu){
            if(!StringUtils.isEmpty(sysMenu.getName())){
                where.like("name","%"+sysMenu.getName()+"%");
            }
        }

        return sysMenuMapper.selectList(entityWrapper);
    }

    @Override
    public List<SysMenu> find(Long roleId) {
        return find(null,roleId);
    }

    @Override
    public List<SysMenu> find(Collection<Long> roleIds) {
        return null;
    }
}
