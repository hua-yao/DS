package com.huayao.config;


import com.alibaba.fastjson.JSON;
import com.huayao.constant.WebConst;
import io.jsonwebtoken.impl.Base64UrlCodec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.huayao.uitl.CookieUtil;
import util.HttpclientUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author huayao
 */
@Component
public class AuthInerceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录回来的话 newtoken 保存到cookie中
        String newToken = request.getParameter("newToken");
        String userId = null;
        if (newToken != null) {
            //写到cookie
            CookieUtil.setCookie(request, response, "token", newToken, WebConst.cookieMaxAge, false);
        }
        //检查cookie中是否有token,如果有token 从token中取出昵称放到request中
        String token = CookieUtil.getCookieValue(request, "token", false);
        if (token != null) {
            String privateToken = StringUtils.substringBetween(token, ".");
            Base64UrlCodec base64UrlCodec = new Base64UrlCodec();
            byte[] bytes = base64UrlCodec.decode(privateToken);
            String privatejson = new String(bytes, "UTF-8");
            Map userMap = JSON.parseObject(privatejson, Map.class);
            String nickName = (String) userMap.get("nickName");
            userId = (String) userMap.get("userId");
            request.setAttribute("nickName", nickName);

        }
        //如果这个请求需要认证登录，把token发送到认证中心，进行效验
        HandlerMethod handlerMethod = (HandlerMethod) handler;//获取反射
        LoginRequire methodAnnotation = handlerMethod.getMethodAnnotation(LoginRequire.class);
        if (methodAnnotation != null) {
            if (token != null) {
                String currentIp = request.getHeader("x-forwarded-for");
                //发送token到认证中心，认证服务是restfuu请求的controller，使用httpclinet
                String result = HttpclientUtil.doGet(WebConst.VERIFY_URL + "?currentIp="+currentIp+"&token=" + token);
                if ("success".equals(result)) {
                    request.setAttribute("userId", userId);
                } else {
                    String originUrl = URLEncoder.encode(request.getRequestURL().toString(), "utf-8");
                    response.sendRedirect("http://passport.gmall.com/index?originUrl=" + originUrl);
                }
            } else {
                String originUrl = URLEncoder.encode(request.getRequestURL().toString(), "utf-8");
                response.sendRedirect("http://passport.gmall.com/index?originUrl=" + originUrl);
            }
        }

        //认证中心返回认证结果，如果是false 从定向到登录页面，如果是success把userid写入request中
        return true;
    }

}





















