package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request,
                        HttpServletResponse response, HttpSession session) {
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userService.queryUserByLoginActAndPwd(map);
        ReturnObject returnObject = new ReturnObject();
        if (user == null){
            //登入失败，用户名或者密码错误
            returnObject.setCode(Contants.RETURN_OBJECT_FAIL);
            returnObject.setMessage("用户名或者密码错误");
        }else{
            if (DateUtils.formateDateTime(new Date()).compareTo(user.getExpireTime())>0){
                //登入失败，账号已过期
                returnObject.setCode(Contants.RETURN_OBJECT_FAIL);
                returnObject.setMessage("账号已过期");
            }else if ("0".equals(user.getLockState())){
                //登入失败，账号被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_FAIL);
                returnObject.setMessage("账号被锁定");
            }else if (user.getAllowIps().contains(request.getRemoteAddr())){
                //登入失败，ip受限
                returnObject.setCode(Contants.RETURN_OBJECT_FAIL);
                returnObject.setMessage("ip受限");
            }else {
                //登入成功
                returnObject.setCode(Contants.RETURN_OBJECT_SUCCESS);
                session.setAttribute(Contants.SESSION_USER,user);

                //如果需要记住密码。则往外写cookie
                if ("true".equals(isRemPwd)){
                    Cookie c1 = new Cookie("loginAct", user.getLoginAct());
                    c1.setMaxAge(10*24*60*60);
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd", user.getLoginPwd());
                    c1.setMaxAge(10*24*60*60);
                    response.addCookie(c2);
                }else {
                    //把没有过期的Cookie删除
                    Cookie c1 = new Cookie("loginAct", "1");
                    c1.setMaxAge(0);
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd", "1");
                    c2.setMaxAge(0);
                    response.addCookie(c2);
                }

            }
        }
        return returnObject;
    }

    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        //清空cookie
        Cookie c1 = new Cookie("loginAct", "1");
        c1.setMaxAge(0);
        response.addCookie(c1);
        Cookie c2 = new Cookie("loginPwd", "1");
        c2.setMaxAge(0);
        response.addCookie(c2);
        //销毁session
        session.invalidate();
        //跳转到首页
        return "redirect:/";
    }
}
