package cn.campus.platfrom.mapper;

import cn.campus.platfrom.entity.Test;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TestMapper {

    @Insert("insert into test values (null,#{test.name})")
    @Options(useGeneratedKeys = true,keyProperty = "test.id")
    Long insertTest(@Param("test") Test test);

    @Select("select * from test where id = #{id}")
    Test getTest(Long id);

    @Update("update test set name=#{test.name} where id=#{test.id}")
    Long updateTest(@Param("test") Test test);

    @ResultType(Test.class)
    @Select("select * from test")
    List<Test> getTestList();
}
