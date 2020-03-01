package huayao.com.gmallusermanager.controller;

import bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserManageService;

import java.util.List;

/**
 * @program: dainShangDemo
 * @description: 控制类
 * @author: HuaYao
 * @create: 2020-01-02 17:19
 **/
@RestController
public class UserManageController {
    @Autowired
    UserManageService userManageService;

    @RequestMapping("/users")
    public ResponseEntity<List<UserInfo>> getUserList(UserInfo userInfo){
        List<UserInfo> userInfoList = userManageService.getUserInfoList(userInfo);
        return ResponseEntity.ok().body(userInfoList);
    }
}
