package cn.campus.platfrom.service.impl;

import cn.campus.platfrom.Constants;
import cn.campus.platfrom.entity.SysRole;
import cn.campus.platfrom.entity.SysRoleMenu;
import cn.campus.platfrom.mapper.SysRoleMapper;
import cn.campus.platfrom.mapper.SysRoleMenuMapper;
import cn.campus.platfrom.mapper.SysUserRoleMapper;
import cn.campus.platfrom.service.SysRoleService;
import cn.campus.platfrom.util.LongUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRole> find(SysRole sysRole, Long userId) {

        EntityWrapper<SysRole> entityWrapper=new EntityWrapper<>();
        Wrapper<SysRole> where = entityWrapper.where("is_delete={0}", Constants.NOT_DELETE);

        if(null!=sysRole){
            if(!StringUtils.isEmpty(sysRole.getName())){
                where.like("name","%"+sysRole.getName()+"%");
            }
        }

        if(!LongUtils.isNull(userId)){
            List<Long> roleIds=sysUserRoleMapper.findRoleIds(userId);
            where.in("id",roleIds);
        }

        return sysRoleMapper.selectList(where);
    }

    @Override
    public List<SysRole> find(Long userId) {
        return find(null,userId);
    }

    @Override
    public List<Long> findRoleIds(SysRole sysRole, Long userId) {
        return null;
    }

    @Override
    public List<Long> findRoleIds(Long userId) {
        return findRoleIds(null,userId);
    }

    @Override
    public SysRole inser(SysRole sysRole, List<String> menuIds) {

        if(sysRole==null){
            throw new NullPointerException("角色为空");
        }

        sysRoleMapper.insert(sysRole);

        long roleId=sysRole.getId();

        if(null!=menuIds && menuIds.size()>0){
            for(String s:menuIds){
                long menuId=Long.parseLong(s);
                SysRoleMenu sysRoleMenu=new SysRoleMenu();
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }

        return sysRole;
    }

    @Override
    public SysRole inser(SysRole sysRole, String menuIds) {
        Iterable<String> split = Splitter.on(",").trimResults().omitEmptyStrings().split(menuIds);
        return inser(sysRole, Lists.newArrayList(split));
    }
}
