package cn.campus.platfrom.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/sysResource")
public class SysResourceHandler {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/list")
    public String list(){
        return "resource/list";
    }

    @ResponseBody
    @RequestMapping("/data")
    public String data(
        @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
        @RequestParam(value = "limit",required = false,defaultValue = "10") Integer limit
    ){
        String result = restTemplate.getForObject("http://www.layui.com/demo/table/user/?page="+page+"&limit="+limit, String.class);
        return result;
    }

}
