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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName LostRecordController
 * @Description TODO
 * @Author MI
 * @Date 2019/12/26 1:31
 * @Version 1.0
 */
@Controller
public class LostRecordController {

    @Autowired
    private LostRecordDao lostRecordDao;

    @Autowired
    private LostUsersDao lostUsersDao;

    /**
     * @Description 跳转用户添加的失物招领信息页面
     * @Author  xingming
     * @Date   2019/12/26 20:14
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/toUserLost")
    public String toUserLost(HttpServletRequest request,Model model){
        int lostUserId = (int) request.getSession().getAttribute("userId");
        System.out.println(lostUserId);
        ArrayList<Object> lists = new ArrayList<>();
        List<LostRecord> lostRecords = new ArrayList<>();
        try {
            lostRecords = lostRecordDao.findAllByLostUserId(lostUserId);
            for (LostRecord recordObj : lostRecords) {
                HashMap<String, Object> map = (HashMap<String, Object>) MapUtil.objectToMap(recordObj);
                LostUsers user = lostUsersDao.findById(recordObj.getLostUserId());
                if(user == null) continue;
                map.put("userName",user.getUserName());
                map.put("phone",user.getPhone());
                lists.add(map);
                System.out.println(map.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("lostRecords",lists);
        return "/userLost";
    }

    /**
     * @Description  跳转增加失物招领信息页面
     * @Author  xingming
     * @Date   2019/12/26 20:15
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/toAddLost")
    public String toAddLost(){
        return "addLost";
    }

    /**
     * @Description  增加失物招领信息
     * @Author  xingming
     * @Date   2019/12/26 20:16
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/addLost")
    public String addLost(LostRecord lostRecord, Model model){
        LostRecord record = lostRecordDao.save(lostRecord);
        return "redirect:/toUserLost";
    }

    /**
     * @Description 跳转修改失物招领信息页面
     * @Author  xingming
     * @Date   2019/12/26 20:16
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/toUpdateLost")
    public String toUpdateLost(int id,Model model){
        LostRecord record = lostRecordDao.findById(id);
        model.addAttribute("record",record);
        return "updateLost";
    }

    /**
     * @Description  修改失物招领信息
     * @Author  xingming
     * @Date   2019/12/26 20:17
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/updateLost")
    public String updateLost(LostRecord lostRecord){
        LostRecord save = lostRecordDao.save(lostRecord);
        return "redirect:/toUserLost";
    }

    /**
     * @Description  删除失物招领信息
     * @Author  xingming
     * @Date   2019/12/26 20:17
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/deleteLost")
    public String deleteLost(LostRecord lostRecord){
        lostRecordDao.delete(lostRecord);
        return "redirect:/toUserLost";
    }

    /**
     * @Description  完成失物招领
     * @Author  xingming
     * @Date   2019/12/26 20:18
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping("/finish")
    public String finish(LostRecord lostRecord){
        LostRecord record = lostRecordDao.findById(lostRecord.getId());
        int status = record.getStatus();
        System.out.println("----------------------------------------------------"+status);
        if (status == 0){
            record.setStatus(1);
        }
        lostRecordDao.save(record);
        return "redirect:/toUserLost";
    }



}
