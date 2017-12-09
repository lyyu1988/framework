package cn.campus.platfrom.service.impl;

import cn.campus.platfrom.Constants;
import cn.campus.platfrom.entity.SysUser;
import cn.campus.platfrom.mapper.SysUserMapper;
import cn.campus.platfrom.service.SysUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("sysUserService")
@CacheConfig(cacheNames = "sysUser")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @CachePut(key = "#{sysUser.id}")
    public SysUser insert(SysUser sysUser) {
         sysUserMapper.insert(sysUser);
         return sysUser;
    }

    @Override
    public Long addErrorCount(String userName) {
        return sysUserMapper.addErrorCount(userName);
    }

    @Override
    @Cacheable(key = "#{id}")
    public SysUser getById(Long id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public List<SysUser> list(SysUser sysUser) {
        EntityWrapper<SysUser> entityWrapper=new EntityWrapper<>();
        Wrapper<SysUser> where = entityWrapper.where("is_delete={0}", Constants.NOT_DELETE);
        if(!StringUtils.isEmpty(sysUser.getUserName())){
            where.and("user_name={0}",sysUser.getUserName());
        }
        if(!StringUtils.isEmpty(sysUser.getEmail())){
            where.and("email={0}",sysUser.getEmail());
        }
        return sysUserMapper.selectList(entityWrapper);
    }

    @Override
    public List<SysUser> getByUsername(String userName) {
        SysUser sysUser=new SysUser();
        sysUser.setUserName(userName);
        return this.list(sysUser);
    }
}
