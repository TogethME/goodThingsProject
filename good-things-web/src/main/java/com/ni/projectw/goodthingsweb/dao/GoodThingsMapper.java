package com.ni.projectw.goodthingsweb.dao;


import com.ni.projectw.goodthingsweb.pojo.GoodThings;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GoodThingsMapper {

    // 插入 并返回自增ID
    @Insert("INSERT INTO tb_test(key, value) VALUES(#{key}, #{value})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'tb_test')", before = false, keyProperty = "id", resultType = int.class)
    int insert(GoodThings model);

    // 根据 ID 查询
    @Select("SELECT * FROM tb_test WHERE id=#{id}")
    GoodThings select(int id);

    // 查询全部
    @Select("SELECT * FROM tb_test")
    List<GoodThings> selectAll();

}
