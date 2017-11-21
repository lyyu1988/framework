package cn.campus.platfrom.handler;

import cn.campus.platfrom.entity.SysUser;
import cn.campus.platfrom.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sysUser")
public class SysUserHandler {

    @Autowired
    private SysUserService sysUserService;

    @ResponseBody
    @RequestMapping("/list")
    public List<SysUser> list(){
        SysUser sysUser=new SysUser();
        sysUser.setUserName("d");
        return sysUserService.list(sysUser);
    }

}
