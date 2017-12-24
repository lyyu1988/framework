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
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginHandler {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginPage(Map<String,Object> model){
        return "common/login";
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(String username, String password, Map<String,Object> model, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        /*
        Principal userPrincipal = request.getUserPrincipal();
        System.out.println(userPrincipal);
        */
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        String errorMsg = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            errorMsg = "用户名/密码错误";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            errorMsg = "其他错误：" + e.getMessage();
        }

        if(errorMsg != null) {//出错了，返回登录页面
            sysUserService.addErrorCount(username);
            model.put("errorMsg",errorMsg);
            return loginPage(model);
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
