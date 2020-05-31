package com.prj.controller;

import com.prj.dao.*;
import com.prj.model.Admin;
import com.prj.model.Garbage;
import com.prj.model.News;
import com.prj.tools.ResponseResult;
import com.prj.tools.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.expression.Maps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class WebController {
    @Autowired
    private GarbageMapper garbageMapper;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private RecycleMapper recycleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/welcome" ,method = RequestMethod.GET)
    public ModelAndView welcome(){
        return new ModelAndView("welcome");
    }

    //单独上传图片
    @ResponseBody
    @RequestMapping(value = "/uploadImage" ,method = RequestMethod.POST)
    public Map<String,Object> uploadImage(@RequestParam("file") MultipartFile multipartFile){
        String image= UploadFile.uploadImage(multipartFile);
        Map<String,Object> resultMap= new HashMap<>();
        resultMap.put("status",1);
        resultMap.put("msg","上传成功！");
        resultMap.put("image",image);
        return resultMap;
    }


    //登录
    @RequestMapping(value = "/loginAdmin" ,method = RequestMethod.POST)
    public ModelAndView loginAdmin(@RequestParam("username") String username, @RequestParam("password") String password,
                                    Model model, HttpServletRequest request){
            HttpSession session=request.getSession();
            Admin admin=adminMapper.getByName(username);
            if(admin==null){
                model.addAttribute("msg","账号不存在！");
                return new ModelAndView("login");
            }
            if(!admin.getPassword().equals(password)) {
                model.addAttribute("msg","密码错误！");
                return new ModelAndView("login");
            }
            session.setAttribute("admin", admin);
            return new ModelAndView("welcome");
    }

    @RequestMapping(value = "/editAdminP" ,method = RequestMethod.GET)
    public ModelAndView editAdminP(Model model, HttpServletRequest request){
        HttpSession session=request.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        if(admin==null){
            return new ModelAndView("login");
        }
        model.addAttribute("admin",admin);
        return new ModelAndView("editAdmin");
    }

    //编辑管理员信息
    @RequestMapping(value = "/editAdmin" ,method = RequestMethod.POST)
    public ModelAndView editAdmin(@RequestParam("name") String name,@RequestParam("id") Integer id,
                                  Model model, HttpServletRequest request,@RequestParam("avatar") String avatar){
        Admin admin=adminMapper.selectByPrimaryKey(id);
        admin.setName(name);
        admin.setAvatar(avatar);
        adminMapper.updateByPrimaryKeySelective(admin);
        model.addAttribute("admin",admin);
        model.addAttribute("msg","修改成功！");
        return new ModelAndView("editAdmin");
    }

    //用户管理
    @RequestMapping(value = "/userListP" ,method = RequestMethod.GET)
    public ModelAndView userListP(){
        return new ModelAndView("userList");
    }
    //加载所有用户列表
    @RequestMapping(value = "/userList" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult userList(int page,int limit){
        return new ResponseResult(0,userMapper.countUser(),"",true,userMapper.getList(page-1,limit));

    }

    //加载搜索用户列表
    @RequestMapping(value = "/searchUserList" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult searchUserList(int page,int limit,String keyword){
        return new ResponseResult(0,userMapper.countUser(),"",true,userMapper.getListByKeyword("%"+keyword+"%",page-1,limit));

    }

    //删除用户列表
    @RequestMapping(value = "/deleteUser" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult deleteUser(@RequestBody Map<String,Object> map){
        userMapper.deleteByPrimaryKey((Integer) map.get("userId"));
        return new ResponseResult(0,"删除成功",true);
    }
    //垃圾管理
    @RequestMapping(value = "/garbageListP" ,method = RequestMethod.GET)
    public ModelAndView garbageListP(){
        return new ModelAndView("garbageList");
    }

    //加载所有垃圾列表
    @RequestMapping(value = "/garbageList" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult garbageList(int page,int limit){
        return new ResponseResult(0,userMapper.countUser(),"",true,garbageMapper.getList(page-1,limit));

    }

    //垃圾编辑
    @RequestMapping(value = "/editGarbageP" ,method = RequestMethod.GET)
    public ModelAndView editGarbageP(@RequestParam("id") Integer id,Model model){
        Garbage garbage=garbageMapper.selectByPrimaryKey(id);
        model.addAttribute("garbage",garbage);
        return new ModelAndView("editGarbage");
    }

    //垃圾编辑
    @RequestMapping(value = "/editGarbage" ,method = RequestMethod.POST)
    public ResponseResult editGarbage(@RequestParam("id") Integer id,@RequestParam("name") String name,
                                    @RequestParam("type") Integer type,Model model){
        Garbage garbage=garbageMapper.selectByPrimaryKey(id);
        garbage.setgName(name);
        garbage.setgType(type);
        garbageMapper.updateByPrimaryKeySelective(garbage);
        return new ResponseResult(0,"修改成功",true);
    }

    //删除垃圾
    @RequestMapping(value = "/deleteGarbage" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult deleteGarbage(@RequestBody Map<String,Object> map){
        garbageMapper.deleteByPrimaryKey((Integer) map.get("id"));
        return new ResponseResult(0,"删除成功",true);
    }

    //垃圾添加
    @RequestMapping(value = "/addGarbageP" ,method = RequestMethod.GET)
    public ModelAndView addGarbageP(){
        return new ModelAndView("addGarbage");
    }

    //垃圾添加
    @RequestMapping(value = "/addGarbage" ,method = RequestMethod.POST)
    public ModelAndView addGarbage(@RequestParam("name") String name,
                                      @RequestParam("type") Integer type,Model model){
        Garbage garbage=new Garbage();
        garbage.setgName(name);
        garbage.setgType(type);
        garbageMapper.insertSelective(garbage);
        model.addAttribute("msg","添加成功！");
        return new ModelAndView("addGarbage");
    }

    //新闻添加
    @RequestMapping(value = "/addNewsP" ,method = RequestMethod.GET)
    public ModelAndView addNewsP(){
        return new ModelAndView("addNews");
    }

    //新闻添加
    @RequestMapping(value = "/addNews" ,method = RequestMethod.POST)
    public ModelAndView addNews(@RequestParam("title") String title,@RequestParam("authorName") String authorName,
                                @RequestParam(value = "authorAvatar",required = false) String authorAvatar,
                                @RequestParam("content") String content,@RequestParam("imageUrl") String imageUrl,Model model){
        News news=new News();
        news.setTitle(title);
        news.setAuthorName(authorName);
        news.setAuthorAvatar(authorAvatar);
        news.setImage(imageUrl);
        news.setContent(content);
        newsMapper.insertSelective(news);
        model.addAttribute("msg","添加成功！");
        return new ModelAndView("addNews");
    }

    //新闻管理
    @RequestMapping(value = "/newsListP" ,method = RequestMethod.GET)
    public ModelAndView newsListP(){
        return new ModelAndView("newsList");
    }

    //加载所有新闻列表
    @RequestMapping(value = "/newsList" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult newsList(int page,int limit){
        return new ResponseResult(0,userMapper.countUser(),"",true,newsMapper.getList(page-1,limit));

    }


    //编辑新闻
    @RequestMapping(value = "/editNewsP" ,method = RequestMethod.GET)
    public ModelAndView editNewsP(@RequestParam("id") Integer id,Model model){
        News news=newsMapper.selectByPrimaryKey(id);
        model.addAttribute("news",news);
        return new ModelAndView("editNews");
    }

    //编辑新闻
    @RequestMapping(value = "/editNews" ,method = RequestMethod.POST)
    public ResponseResult editNews(@RequestParam("id") Integer id,@RequestParam("title") String title,@RequestParam("authorName") String authorName,
                                   @RequestParam(value = "authorAvatar",required = false) String authorAvatar,
                                   @RequestParam("content") String content,@RequestParam("imageUrl") String imageUrl,Model model){
        News news=newsMapper.selectByPrimaryKey(id);
        news.setTitle(title);
        news.setAuthorName(authorName);
        news.setAuthorAvatar(authorAvatar);
        news.setImage(imageUrl);
        news.setContent(content);
        newsMapper.updateByPrimaryKeyWithBLOBs(news);
        return new ResponseResult(0,"修改成功",true);
    }

    //删除垃圾
    @RequestMapping(value = "/deleteNews" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult deleteNews(@RequestBody Map<String,Object> map){
        newsMapper.deleteByPrimaryKey((Integer) map.get("id"));
        return new ResponseResult(0,"删除成功",true);
    }

}
