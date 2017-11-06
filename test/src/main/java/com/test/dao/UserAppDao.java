package com.test.dao;

import com.test.entity.UserApp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserAppDao {
    @Select("select * from user_app where id = #{id}")
    UserApp getUserById(Long id);

    @Update("update user_app set username=#{username} where id=#{id}")
    void updateUserApp(@Param("username") String username, @Param("id") Long id);
}
