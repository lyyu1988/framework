package cn.campus.platfrom;

import org.junit.Test;

import java.lang.management.ManagementFactory;

public class MXBeanTest {

    @Test
    public void test(){
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
    }

}
