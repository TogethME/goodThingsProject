package com.ni.projectw.goodthingsweb.dao;

import com.ni.projectw.goodthingsweb.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    // 插入 并返回自增ID
    @Insert("INSERT INTO TM_USER(key, value) VALUES(#{key}, #{value})")
    //@SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'tb_test')", before = false, keyProperty = "id", resultType = int.class)
    @Options(useGeneratedKeys=true, keyProperty="id",keyColumn = "id")
    int insert(User model);

    // 根据 ID 查询
    @Select("SELECT * FROM TM_USER WHERE id=#{id}")
    User select(int id);

    // 查询全部
    @Select("SELECT * FROM TM_USER")
    List<User> selectAll();
}
