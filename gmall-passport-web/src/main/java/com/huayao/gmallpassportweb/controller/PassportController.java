package com.huayao.gmallpassportweb.controller;

import bean.UserInfo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserManageService;
import com.huayao.uitl.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huayao
 */
@Controller
public class PassportController {

    @Value("${passport.key}")
    String passport_key;

    @Reference
    UserManageService userManageService;

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        String originUrl = request.getParameter("originUrl");
        request.setAttribute("originUrl",originUrl);
        return "index";
    }

    @PostMapping("login")
    @ResponseBody
    public String login(UserInfo userInfo, HttpServletRequest httpServletRequest){
        //取ip地址
        String remoteAddr = httpServletRequest.getHeader("x-forwarded-for");
        UserInfo users = null;
        try {
             users = userManageService.login(userInfo);
        }catch (Exception e){
           return null;
        }
        if (users.getId()==null){
            return "fail";
        }else {
            Map map = new HashMap<>();
            map.put("userId",users.getId());
            map.put("nickName",users.getNickName());
            String token = JwtUtil.encode(passport_key, map, remoteAddr);
            System.out.println("token="+token);
            return token;
        }
    }

    @GetMapping("verify")
    @ResponseBody
    public String verify(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getParameter("token");
        String curIp = httpServletRequest.getParameter("currentIp");
        Map<String, Object> map = JwtUtil.decode(token, passport_key, curIp);
        String userId = (String) map.get("userId");
        boolean verify = userManageService.verify(userId);
        if (verify){
            return "success";
        }else {
            return "fail";
        }
    }


}









































