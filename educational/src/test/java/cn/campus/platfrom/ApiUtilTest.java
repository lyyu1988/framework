package cn.campus.platfrom;

import cn.campus.platfrom.exception.ParamException;
import cn.campus.platfrom.util.ApiUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class ApiUtilTest {
    @Test
    public void getSignString() throws Exception {

        long timestamp=System.currentTimeMillis();

        System.out.println("=========================="+timestamp);

        Map<String,String[]> map=new HashMap<>();
        map.put("name",new String[]{"test"});
        map.put("appId",new String[]{"wxf6f5e8acdfff94920bc0debe48fe0c5b1"});
        map.put("timestamp",new String[]{String.valueOf(timestamp)});

        String secret="38383da664eb4f038cb97e3ed032930a";

        try {
            String signString = ApiUtil.getSignString(map, secret);
            System.out.println(signString);
        } catch (ParamException e) {
            e.printStackTrace();
        }

    }

}