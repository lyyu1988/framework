package cn.campus.platfrom.util;

import cn.campus.platfrom.Constants;
import cn.campus.platfrom.entity.AppClient;
import cn.campus.platfrom.exception.AppIdException;
import cn.campus.platfrom.exception.ParamException;
import cn.campus.platfrom.exception.SignException;
import cn.campus.platfrom.exception.TimeoutException;
import cn.campus.platfrom.service.AppClientService;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class ApiUtil {

    private static AppClientService appClientService;

    public static boolean checkApi(Map<String,String[]> params) throws SignException, AppIdException, ParamException, TimeoutException {
        // 验证是否超时
        Long timestamp=null;
        try {
            if(params.containsKey(Constants.Api.TIMESTAMP)){
                if(params.get(Constants.Api.TIMESTAMP).length>0){
                    timestamp=Long.parseLong(params.get(Constants.Api.TIMESTAMP)[0]);
                }
            }
        }catch (NumberFormatException e){
            throw new ParamException("不合法的参数");
        }
        if(null==timestamp){
            throw new ParamException("不合法的参数");
        }
        if(timeoutCheck(timestamp)){
            throw new TimeoutException("接口超时");
        }

        // 验证是否包含凭证
        String sign=null;
        if(params.containsKey(Constants.Api.SIGN)){
            if(params.get(Constants.Api.SIGN).length>0){
                sign=params.get(Constants.Api.SIGN)[0];
            }
        }
        if(StringUtils.isEmpty(sign)){
            throw new SignException("不合法的凭证类型");
        }

        // 验证appId是否正确
        String appId=null;
        if(params.containsKey(Constants.Api.APP_ID)){
            if(params.get(Constants.Api.APP_ID).length>0){
                appId=params.get(Constants.Api.APP_ID)[0];
            }
        }
        if(StringUtils.isEmpty(appId)){
            throw new AppIdException("不合法的 AppID ，请开发者检查 AppID 的正确性，避免异常字符，注意大小写");
        }
        AppClient appClient=appClientService.getById(appId);
        if(null==appClient){
            throw new AppIdException("不合法的 AppID ，请开发者检查 AppID 的正确性，避免异常字符，注意大小写");
        }
        String secret=appClient.getSecret();

        if(!sign.equals(getSignString(params,secret))){
            throw new SignException("不合法的凭证类型");
        }

        return true;
    }

    public static String getSignString(Map<String, String[]> requestData, String secret) throws ParamException {
        String encryptValue = generatePlainText(requestData);
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, secret).hmacHex(encryptValue);
    }

    private static boolean timeoutCheck(long timestamp){
        long now=System.currentTimeMillis();
        return ((now-timestamp)/1000/60)>Constants.Api.TIMEOUT_MINUTES;
    }

    private static String generatePlainText(Map<String,String[]> returnData) throws ParamException {
        //排序参数
        List<String> list = new ArrayList<>(returnData.keySet());

        if(list.size()==0){
            throw new ParamException("不合法的参数");
        }

        Collections.sort(list);

        String plainText="";
        for(String key:list){
            if(Constants.Api.SIGN.equals(key)){
                continue;
            }
            String[] data=returnData.get(key);
            if(data.length==0){
                throw new ParamException("不合法的参数");
            }
            plainText+=key+"="+data[0]+"&";
        }
        return plainText.substring(0,plainText.length()-1);
    }

    @Autowired
    public void setAppClientService(AppClientService appClientService) {
        ApiUtil.appClientService = appClientService;
    }

}
