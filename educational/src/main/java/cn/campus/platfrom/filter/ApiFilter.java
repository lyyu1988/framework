package cn.campus.platfrom.filter;

import cn.campus.platfrom.Constants;
import cn.campus.platfrom.exception.AppIdException;
import cn.campus.platfrom.exception.ParamException;
import cn.campus.platfrom.exception.SignException;
import cn.campus.platfrom.exception.TimeoutException;
import cn.campus.platfrom.util.ApiUtil;
import cn.campus.platfrom.util.ReturnData;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.filter.authc.AnonymousFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ApiFilter extends AnonymousFilter {

    private  static Logger logger = LogManager.getLogger(ApiFilter.class);

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
        Map<String, String[]> parameterMap = request.getParameterMap();

        response.setContentType("text/html;charset=utf-8");

        ReturnData returnData=new ReturnData();

        PrintWriter pw=null;
        try {
            pw=new PrintWriter(response.getOutputStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        try {
            if(ApiUtil.checkApi(parameterMap)){
                return true;
            }
        } catch (SignException e) {
            logger.error(e.getMessage());
            returnData.setMsg(e.getMessage());
            returnData.setCode(Constants.ErrorCode.SIGN_ERROR);
        } catch (AppIdException e) {
            logger.error(e.getMessage());
            returnData.setMsg(e.getMessage());
            returnData.setCode(Constants.ErrorCode.APP_ID_ERROR);
        } catch (ParamException e) {
            logger.error(e.getMessage());
            returnData.setMsg(e.getMessage());
            returnData.setCode(Constants.ErrorCode.PARAM_ERROR);
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            returnData.setMsg(e.getMessage());
            returnData.setCode(Constants.ErrorCode.PARAM_TIMEOUT_ERROR);
        }

        pw.write(JSON.toJSONString(returnData));
        if(pw!=null){
            pw.close();
        }

        return false;
    }
}
