package cn.campus.platfrom;

import cn.campus.platfrom.mapper.SysRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class SpringTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void test(){
        sysRoleMapper.getSysRoleByIds("1,2");
    }

}
