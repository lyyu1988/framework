package cn.campus.platfrom.handler;

import cn.campus.platfrom.entity.Test;
import cn.campus.platfrom.entity.UserApp;
import cn.campus.platfrom.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestHandler {

    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping("/test")
    public UserApp test(){
        return testService.getUserApp(111941l);
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(){
        Test test=new Test();
        test.setName("test");
        Test t=testService.insertTest(test);
        System.out.println(test.getId());
        return t.getId().toString();
    }

    @ResponseBody
    @RequestMapping("/getTest")
    public Test getTest(Long id){
        return testService.getTest(id);
    }

    @ResponseBody
    @RequestMapping("/updateTest")
    public Test updateTest(Long id,String name){
        Test test=new Test();
        test.setName(name);
        test.setId(id);
        return testService.updateTest(test);
    }

    @ResponseBody
    @RequestMapping(value="/test2")
    public Test test2(){
        Test test=new Test();
        test.setName("你好");
        return test;
    }
}
