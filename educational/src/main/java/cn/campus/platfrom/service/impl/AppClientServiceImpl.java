package cn.campus.platfrom.service.impl;

import cn.campus.platfrom.entity.AppClient;
import cn.campus.platfrom.mapper.AppClientMapper;
import cn.campus.platfrom.service.AppClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("appClientService")
@CacheConfig(cacheNames = "appClient")
public class AppClientServiceImpl implements AppClientService {

    @Autowired
    private AppClientMapper appClientMapper;

    @Override
    @Cacheable(key = "#id")
    public AppClient getById(String id) {
        return appClientMapper.selectById(id);
    }

    @Override
    @CachePut(key = "#appClient.id")
    public AppClient insert(AppClient appClient) {
        appClientMapper.insert(appClient);
        return appClient;
    }
}
