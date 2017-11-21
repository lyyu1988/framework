package cn.campus.platfrom.service.impl;

import cn.campus.platfrom.entity.SysUser;
import cn.campus.platfrom.mapper.SysUserMapper;
import cn.campus.platfrom.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
        return sysUserMapper.getById(id);
    }

    @Override
    public List<SysUser> list(SysUser sysUser) {
        return sysUserMapper.getSysUser(sysUser);
    }

    @Override
    public List<SysUser> getByUsername(String userName) {
        return sysUserMapper.getByUsername(userName);
    }
}
