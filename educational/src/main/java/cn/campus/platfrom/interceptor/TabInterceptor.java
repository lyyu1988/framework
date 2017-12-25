package cn.campus.platfrom.interceptor;

import cn.campus.platfrom.Constants;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class TabInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String menuId=request.getParameter(Constants.TabParam.TAB_MENU_ID);
        if(!StringUtils.isEmpty(menuId)){
            Map<String, Object> model = modelAndView.getModel();
            if(!model.containsKey(Constants.TabParam.TAB_MENU_ID)){
                model.put(Constants.TabParam.TAB_MENU_ID,menuId);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
