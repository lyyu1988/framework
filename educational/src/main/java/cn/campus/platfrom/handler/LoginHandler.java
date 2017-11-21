package cn.campus.platfrom.handler;

import cn.campus.platfrom.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginHandler {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/loginPage")
    public String loginPage(){
        return "common/login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        boolean flag=true;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            sysUserService.addErrorCount(username);
            flag=false;
        } catch (IncorrectCredentialsException e) {
            sysUserService.addErrorCount(username);
            flag=false;
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            sysUserService.addErrorCount(username);
            flag=false;
        }

        if(!flag){
            return "common/login";
        }
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        // 获取保存的URL
        if (savedRequest == null || savedRequest.getRequestUrl() == null) {
            return "redirect:/common/index";
        }
        String url=savedRequest.getRequestUrl();
        return "redirect:"+url.substring(url.indexOf("/",1));
    }

}
