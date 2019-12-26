package com.gui.springbootdemo.controller;

import com.gui.springbootdemo.dao.LostRecordDao;
import com.gui.springbootdemo.dao.LostUsersDao;
import com.gui.springbootdemo.pojo.LostRecord;
import com.gui.springbootdemo.pojo.LostUsers;
import com.gui.springbootdemo.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName LostUsersService
 * @Description TODO
 * @Author MI
 * @Date 2019/12/25 23:41
 * @Version 1.0
 */
@Controller
public class LostUsersController{

    @Autowired
    private LostUsersDao lostUsersDao;

    @Autowired
    private LostRecordDao lostRecordDao;


    @RequestMapping("/login")
    public String login(){
        return "index";
    }

    /**
     * @Description 登录
     * @Author  xingming
     * @Date   2019/12/26 12:48
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/checkLogin")
    public String check(String phone, String passWord, HttpServletRequest request,Model model){
        System.out.println("成功进入方法");
        System.out.println(phone);
        System.out.println(passWord);
        LostUsers user = lostUsersDao.findByPhone(phone);
        if(user == null){
            model.addAttribute("message","该账号不存在");
            return "index";
        }
        if(!user.getPassWord().equals(passWord)){
            model.addAttribute("message","密码错误");
            return "index";
        }
        request.getSession().setAttribute("userId",user.getId());
        return "redirect:/toAllLost";
    }

    /**
     * @Description 跳转用户登录失物列表
     * @Author  xingming
     * @Date   2019/12/26 12:49
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/toAllLost")
    public String allLost(Model model, HttpServletRequest response) throws Exception {
        ArrayList<Object> lists = new ArrayList<>();
        List<LostRecord> all = lostRecordDao.findAllByStatus(0);
        for (LostRecord recordObj : all) {
            HashMap<String, Object> map = (HashMap<String, Object>) MapUtil.objectToMap(recordObj);
            LostUsers user = lostUsersDao.findById(recordObj.getLostUserId());
            if(user == null) continue;
            map.put("userName",user.getUserName());
            map.put("phone",user.getPhone());
            lists.add(map);
            System.out.println(map.toString());
        }
        model.addAttribute("lostRecords",lists);
        return "/allLost";
    }

    /**
     * @Description  跳转游客方式进入失物列表
     * @Author  xingming
     * @Date   2019/12/26 12:49
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/toTouristAllLost")
    public String toTouristAllLost(Model model,HttpServletRequest request, HttpServletRequest response) throws Exception {
        request.getSession().invalidate();
        ArrayList<Object> lists = new ArrayList<>();
        List<LostRecord> all = lostRecordDao.findAllByStatus(0);
        for (LostRecord recordObj : all) {
            HashMap<String, Object> map = (HashMap<String, Object>) MapUtil.objectToMap(recordObj);
            LostUsers user = lostUsersDao.findById(recordObj.getLostUserId());
            if(user == null) continue;
            map.put("userName",user.getUserName());
            map.put("phone",user.getPhone());
            lists.add(map);
            System.out.println(map.toString());
        }
        model.addAttribute("lostRecords",lists);
        return "/allLost";
    }

    /**
     * @Description 跳转注册页面
     * @Author  xingming
     * @Date   2019/12/26 12:50
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/toregister")
    public String toRegister(){
        return "register";
    }

    /**
     * @Description 注册
     * @Author  xingming
     * @Date   2019/12/26 12:50
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/register")
    public String Register(LostUsers users, Model model, ServletRequest request, ServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(users);
        LostUsers byPhone = lostUsersDao.findByPhone(users.getPhone());
        if (byPhone == null){
            LostUsers i = lostUsersDao.save(users);
            return "redirect:/login";
        }else {
            model.addAttribute("mess","该手机号已注册");
            return "register";
        }
    }

    /**
     * @Description 退出
     * @Author  xingming
     * @Date   2019/12/26 20:14
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
