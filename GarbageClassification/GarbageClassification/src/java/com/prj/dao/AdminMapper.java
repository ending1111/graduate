package com.prj.dao;

import com.prj.model.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

    @Select("SELECT * FROM garbage_classification_db.t_admin where name=#{name} ")
    Admin getByName(@Param("name") String name);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}