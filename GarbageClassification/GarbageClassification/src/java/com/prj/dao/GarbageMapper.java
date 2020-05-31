package com.prj.dao;

import com.prj.model.Garbage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GarbageMapper {

    @Select("SELECT id,g_name gName,g_type gType FROM garbage_classification_db.t_garbage where g_name like #{keyword} order by id desc  LIMIT #{offset}, #{pageSize} ")
    List<Garbage> getByKeyword(@Param("keyword") String keyword,@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);


    @Select("SELECT id,g_name gName,g_type gType FROM garbage_classification_db.t_garbage order by id desc  LIMIT #{offset}, #{pageSize} ")
    List<Garbage> getList(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);


    int deleteByPrimaryKey(Integer id);

    int insert(Garbage record);

    int insertSelective(Garbage record);

    Garbage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Garbage record);

    int updateByPrimaryKey(Garbage record);
}