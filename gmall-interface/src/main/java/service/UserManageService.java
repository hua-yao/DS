package service;


import bean.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: dainShangDemo
 * @description: 接口
 * @author: HuaYao
 * @create: 2020-01-02 17:19
 **/
public interface UserManageService {

    /**
     * 查询数据库保存redis
     * @param userInfo
     * @return
     */
    public UserInfo login(UserInfo userInfo);

    public boolean verify(String userId);
}
