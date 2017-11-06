package cn.campus.platfrom.service.impl;

import cn.campus.platfrom.entity.Test;
import cn.campus.platfrom.entity.UserApp;
import cn.campus.platfrom.mapper.TestMapper;
import cn.campus.platfrom.mapper.UserAppMapper;
import cn.campus.platfrom.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("testService")
@CacheConfig(cacheNames = "test")
public class TestServiceImpl implements TestService {

    @Autowired
    private UserAppMapper userAppMapper;
    @Autowired
    private TestMapper testMapper;

    @Override
    public UserApp getUserApp(Long id) {
        return userAppMapper.getUserById(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#test.id")
    public Test insertTest(Test test) {
        testMapper.insertTest(test);
        return test;
    }

    @Override
    @Cacheable(key = "#id")
    public Test getTest(Long id) {
        return testMapper.getTest(id);
    }

    @Override
    @CachePut(key = "#test.id")
    public Test updateTest(Test test) {
        Long count = testMapper.updateTest(test);
        System.out.println(count);
        return test;
    }
}
