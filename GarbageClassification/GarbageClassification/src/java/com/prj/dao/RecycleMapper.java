package com.prj.dao;

import com.prj.model.Recycle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RecycleMapper {

    @Select("SELECT id,garbage_type garbageType,function FROM garbage_classification_db.t_recycle where garbage_type=#{type} ")
    Recycle getByType(@Param("type") Integer type);

    int deleteByPrimaryKey(Integer id);

    int insert(Recycle record);

    int insertSelective(Recycle record);

    Recycle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recycle record);

    int updateByPrimaryKeyWithBLOBs(Recycle record);

    int updateByPrimaryKey(Recycle record);
}