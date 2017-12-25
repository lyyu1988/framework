package cn.campus.platfrom.mapper;

import cn.campus.platfrom.cache.mybatis.RedisCache;
import cn.campus.platfrom.entity.SysMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class,flushInterval = 6000l)
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    @Select(value={"select * from sys_menu where parent_id=#{parentId} and is_delete=0"})
    @Results(value = {
        @Result(property = "children",column = "id",
            many = @Many(select = "cn.campus.platfrom.mapper.SysMenuMapper.selectByParentId")
        )
    })
    List<SysMenu> selectByParentId(Long parentId);

    @Select(value={"select * from sys_menu where is_delete=0 order by parent_id asc,menu_order asc"})
    @Results(value = {
        @Result(property = "parent",column = "parent_id",
            many = @Many(select = "cn.campus.platfrom.mapper.SysMenuMapper.selectById")
        )
    })
    List<SysMenu> selectWithParent(RowBounds page);

}
