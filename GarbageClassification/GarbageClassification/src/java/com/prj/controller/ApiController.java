package com.prj.controller;

import com.prj.dao.*;
import com.prj.model.Garbage;
import com.prj.model.News;
import com.prj.model.User;
import com.prj.tools.ResultJson;
import com.prj.tools.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/garbage")
public class ApiController {
    @Autowired
    private GarbageMapper garbageMapper;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private RecycleMapper recycleMapper;
    @Autowired
    private UserMapper userMapper;

    /***
     * 登录
     * @param password
     * @return
     */
    @RequestMapping(value = "/loginApp" ,method = RequestMethod.POST)
    public ResultJson loginApp(@RequestParam("username") String username, @RequestParam("password") String password){

        User user=userMapper.getByName(username);
        if(user==null){
            return new ResultJson(-1,"用户不存在");
        }
        if(!user.getPassword().equals(password)){
            return new ResultJson(-1,"密码错误");
        }
        return new ResultJson(1,"登录成功",user.getId());
    }
    /***
     * 获取用户
     * @return
     */
    @RequestMapping(value = "/getUserById" ,method = RequestMethod.GET)
    public ResultJson getUserById(@RequestParam("userId") Integer userId){
        return new ResultJson(1,"获取成功",userMapper.selectByPrimaryKey(userId));
    }

    /***
     * 注册
     * @param password
     * @param username
     * @return
     */
    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    public ResultJson register(@RequestParam("nickname") String nickname, @RequestParam("password") String password,
                               @RequestParam("phone") String phone,@RequestParam("username") String username){
        if(userMapper.getByName(username)!=null){
            return new ResultJson(-1,"用户名已存在");
        }
        User user = new User();
        user.setPhone(phone);
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(password);
        userMapper.insertSelective(user);
        return new ResultJson(1,"注册成功");
    }

    /***
     * 编辑信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/editInfo" ,method = RequestMethod.POST)
    public ResultJson editInfo(@RequestParam("nickname") String nickname,@RequestParam("userId") Integer userId,
                               @RequestParam("phone") String phone){

        User user =userMapper.selectByPrimaryKey(userId);
        user.setNickname(nickname);
        user.setPhone(phone);
        userMapper.updateByPrimaryKeySelective(user);
        return new ResultJson(1,"修改成功",user);
    }



    /***
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param userId
     * @return
     */
    @RequestMapping(value = "/changePassword" ,method = RequestMethod.POST)
    public ResultJson changePassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword,@RequestParam("userId") Integer userId){
        User user=userMapper.selectByPrimaryKey(userId);
        if(!user.getPassword().equals(oldPassword)){
            return new ResultJson(-1,"原密码不正确");
        }
        user.setPassword(newPassword);
        userMapper.updateByPrimaryKeySelective(user);
        return new ResultJson(1,"修改成功");
    }

    //修改头像
    @RequestMapping(value = "/changeAvatar" ,method = RequestMethod.POST)
    public ResultJson changeAvatar(@RequestParam("avatar") MultipartFile multipartFile, @RequestParam("userId") Integer userId){
        User user=userMapper.selectByPrimaryKey(userId);
        String image= UploadFile.uploadImage(multipartFile);
        user.setAvatar(image);
        userMapper.updateByPrimaryKeySelective(user);
        return new ResultJson(1,"修改成功",image);
    }

    /***
     * 模糊搜索查询垃圾
     * @return
     */
    @RequestMapping(value = "/searchByKeyword" ,method = RequestMethod.GET)
    public ResultJson searchByKeyword(@RequestParam("keyword") String keyword,
                                      @RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo){
        List<Garbage> garbages=garbageMapper.getByKeyword("%"+keyword+"%",pageNo * 10, 10);
        List<Garbage> garbageList=new ArrayList<>();
        for(Garbage garbage:garbages){
            garbage.setFunction(recycleMapper.getByType(garbage.getgType()).getFunction());
            garbageList.add(garbage);
        }
        return new ResultJson(1,"获取成功",garbageList);
    }

    /***
     * 新闻列表
     * @return
     */
    @RequestMapping(value = "/getNewsList" ,method = RequestMethod.GET)
    public ResultJson getNewsList(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo){
        List<News> newsList=newsMapper.getList(pageNo * 10, 10);
        return new ResultJson(1,"获取成功",newsList);
    }

    /***
     * 根据垃圾分类查处理方式
     * @return
     */
    @RequestMapping(value = "/getFunctionByType" ,method = RequestMethod.GET)
    public ResultJson getFunctionByType(@RequestParam("garbageType") Integer garbageType){

        return new ResultJson(1,"获取成功",recycleMapper.getByType(garbageType));
    }


}
