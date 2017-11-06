package cn.campus.platfrom.mapper;

import cn.campus.platfrom.entity.UserApp;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserAppMapper {
    @Select("select * from user_app where id = #{id}")
    UserApp getUserById(Long id);

    @Update("update user_app set username=#{username} where id=#{id}")
    UserApp updateUserApp(@Param("username") String username, @Param("id") Long id);

    @Insert("insert into ")
    UserApp insertUserApp(UserApp userApp);
}
