package cn.campus.platfrom.service.impl;

import cn.campus.platfrom.entity.Test;
import cn.campus.platfrom.mapper.TestMapper;
import cn.campus.platfrom.service.TestService;
import com.baomidou.mybatisplus.plugins.Page;
import com.codahale.metrics.annotation.Counted;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testService")
@CacheConfig(cacheNames = "test")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    @CachePut(key = "#test.id")
    //@Counted(monotonic = true)
    public Test insertTest(Test test) {
        testMapper.insert(test);
        return test;
    }

    @Override
    @Cacheable(key = "#id")
    //@Counted(monotonic = true)
    public Test getTest(Long id) {
        return testMapper.selectById(id);
    }

    @Override
    @CachePut(key = "#test.id")
    //@Counted(monotonic = true)
    public Test updateTest(Test test) {
        Integer count = testMapper.updateById(test);
        System.out.println(count);
        return test;
    }

    @Override
    //@RequiresRoles("admin")
    //@Counted(monotonic = true)
    public List<Test> getTestList() {
        return testMapper.selectList(null);
    }

    @Override
    //@Counted(monotonic = true)
    //@Scheduled(cron = "0/5 * * * * ?")
    public Page<Test> getTestPage(Page<Test> page) {
        List<Test> list = testMapper.selectPage(page, null);
        page.setRecords(list);
        return page;
    }
}
