package cn.campus.platfrom.service.impl;

import cn.campus.platfrom.entity.Test;
import cn.campus.platfrom.mapper.TestMapper;
import cn.campus.platfrom.service.TestService;
import com.codahale.metrics.annotation.Counted;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("testService")
@CacheConfig(cacheNames = "test")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    @CachePut(key = "#test.id")
    @Counted(monotonic = true)
    public Test insertTest(Test test) {
        testMapper.insertTest(test);
        return test;
    }

    @Override
    @Cacheable(key = "#id")
    @Counted(monotonic = true)
    public Test getTest(Long id) {
        return testMapper.getTest(id);
    }

    @Override
    @CachePut(key = "#test.id")
    @Counted(monotonic = true)
    public Test updateTest(Test test) {
        Long count = testMapper.updateTest(test);
        System.out.println(count);
        return test;
    }

    @Override
    //@RequiresRoles("admin")
    //@Counted(monotonic = true)
    public List<Test> getTestList() {
        return testMapper.getTestList();
    }

    @Override
    //@Counted(monotonic = true)
    //@Scheduled(cron = "0/5 * * * * ?")
    public Page<Test> getTestPage() {
        Page<Test> testPage=PageHelper.startPage(1, 1).count(true).doSelectPage(()-> testMapper.getTestList());
        return testPage;
    }
}
