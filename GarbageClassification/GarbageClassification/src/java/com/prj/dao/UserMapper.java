package com.prj.dao;

import com.prj.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from t_user where username=#{username} ")
    User getByName(@Param("username") String username);

    @Select("select * from t_user order by id desc LIMIT #{offset}, #{pageSize} ")
    List<User> getList(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select * from t_user  where username like #{keyword}  order by id desc LIMIT #{offset}, #{pageSize} ")
    List<User> getListByKeyword(@Param("keyword") String keyword,@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from t_user order by id desc ")
    int countUser();

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}