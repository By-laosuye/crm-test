package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ActivityController {
    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        //调用service方法查询所有用户
        List<User> userList = userService.queryAllUsers();
        //把数据保存到request作用域
        request.setAttribute("userList",userList);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public Object saveCreateActivity(Activity activity, HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formateDateTime(new Date()));
        activity.setCreateBy(user.getId());
        ReturnObject returnObject = new ReturnObject();

        try {
            //调用service层方法，保存创建的市场活动
            int ret = activityService.saveCreateActivity(activity);
            if (ret > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_FAIL);
                returnObject.setMessage("系统忙，请稍后重试");
            }
        }catch (Exception e){
            e.printStackTrace();

            returnObject.setCode(Contants.RETURN_OBJECT_FAIL);
            returnObject.setMessage("系统忙，请稍后重试");
        }
        return returnObject;
    }
}
