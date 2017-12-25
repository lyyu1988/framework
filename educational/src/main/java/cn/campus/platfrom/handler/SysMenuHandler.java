package cn.campus.platfrom.handler;

import cn.campus.platfrom.Constants;
import cn.campus.platfrom.entity.SysMenu;
import cn.campus.platfrom.service.SysMenuService;
import cn.campus.platfrom.util.MyPage;
import cn.campus.platfrom.util.ReturnData;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysMenu")
public class SysMenuHandler {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private ObjectMapper objectMapper;

    @ModelAttribute
    public SysMenu getSysMenu(@RequestParam(value = "id",required = false) Long id){
        if(null!=id && !id.equals(0l)){
            return sysMenuService.find(id);
        }
        return new SysMenu();
    }

    @RequestMapping("/list")
    public String list(){
        return "sysMenu/list";
    }

    @RequestMapping(value = "/addOrUpdate",method = RequestMethod.GET)
    public String addOrUpdatePage(Map<String,Object> model){
        SysMenu sysMenu=new SysMenu();
        sysMenu.setParentId(0l);
        List<SysMenu> list = sysMenuService.find(sysMenu, null);
        try {
            model.put("sysMenuList", objectMapper.writeValueAsString(list));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "sysMenu/addOrUpdate";
    }

    @ResponseBody
    @RequestMapping(value = "/addOrUpdate",method = RequestMethod.POST)
    public ReturnData addOrUpdate(SysMenu sysMenu){
        ReturnData returnData=new ReturnData();
        try {
            sysMenuService.insert(sysMenu);
            returnData.setMsg("ok");
            returnData.setCode(Constants.ErrorCode.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            returnData.setMsg(e.getMessage());
            returnData.setCode("-1");
        }
        return returnData;
    }

    @ResponseBody
    @RequestMapping("/data")
    public Page<SysMenu> data(
        @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
        @RequestParam(value = "limit",required = false,defaultValue = "10") Integer limit
    ){
        MyPage<SysMenu> p=new MyPage<>();
        p.setCurrent(page);
        p.setSize(limit);
        p=sysMenuService.find(p,null, null);
        p.setCode("200");
        return p;
    }

}
