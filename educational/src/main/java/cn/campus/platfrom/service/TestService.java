package cn.campus.platfrom.service;

import cn.campus.platfrom.entity.Test;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public interface TestService {

    Test insertTest(Test test);

    Test getTest(Long id);

    Test updateTest(Test test);

    List<Test> getTestList();

    Page<Test> getTestPage(Page<Test> page);
}
