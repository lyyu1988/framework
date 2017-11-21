package cn.campus.platfrom.sql;

import cn.campus.platfrom.Constants;
import cn.campus.platfrom.entity.SysUser;
import com.alibaba.druid.util.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SysUserSqlProvider {
    public String getSysUser(@Param("sysUser")SysUser sysUser){
        return new SQL(){{
            SELECT("*");
            FROM("sys_user");
            WHERE("is_delete="+ Constants.NOT_DELETE);
            if(null!=sysUser){
                if(!StringUtils.isEmpty(sysUser.getUserName())){
                    sysUser.setUserName("%"+sysUser.getUserName()+"%");
                    WHERE("user_name like #{sysUser.userName}");
                }
            }
        }}.toString();
    }

}
