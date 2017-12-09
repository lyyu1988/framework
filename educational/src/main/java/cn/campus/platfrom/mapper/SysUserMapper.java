package cn.campus.platfrom.mapper;

import cn.campus.platfrom.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface SysUserMapper extends BaseMapper<SysUser> {

    @Update("update sys_user set error_count=error_count+1 where user_name=#{userName}")
    Long addErrorCount(@Param("userName")String userName);

}
