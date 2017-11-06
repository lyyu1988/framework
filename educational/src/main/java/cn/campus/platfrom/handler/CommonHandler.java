package cn.campus.platfrom.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonHandler {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
