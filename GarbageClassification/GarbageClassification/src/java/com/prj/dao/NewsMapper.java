package com.prj.dao;

import com.prj.model.News;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NewsMapper {

    @Select("SELECT id,title,author_name authorName,author_avatar authorAvatar,content,n_date nDate,image "
            +" FROM garbage_classification_db.t_news order by id desc  LIMIT #{offset}, #{pageSize} ")
    List<News> getList(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    int deleteByPrimaryKey(Integer id);


    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);
}