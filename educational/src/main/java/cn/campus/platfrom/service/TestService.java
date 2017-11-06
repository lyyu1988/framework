package cn.campus.platfrom.service;

import cn.campus.platfrom.entity.Test;
import cn.campus.platfrom.entity.UserApp;

public interface TestService {
    UserApp getUserApp(Long id);

    Test insertTest(Test test);

    Test getTest(Long id);

    Test updateTest(Test test);
}
