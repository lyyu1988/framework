package cn.campus.platfrom.handler;

import cn.campus.platfrom.entity.Test;
import cn.campus.platfrom.service.TestService;
import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestHandler {

    @Autowired
    private TestService testService;

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
    public Test getTest(Long id, HttpServletRequest request){
        Principal userPrincipal = request.getUserPrincipal();
        System.out.println(userPrincipal);
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

    @ResponseBody
    @RequestMapping(value="/list")
    @RequiresRoles("admin")
    public List<Test> list(){
        return testService.getTestList();
    }

    @ResponseBody
    @RequestMapping(value="/page")
    public Page<Test> page(){
        return testService.getTestPage();
    }
}
