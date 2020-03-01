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
     *
     * @param userInfoQuery
     * @return
     */
    public List<UserInfo> getUserInfoList(UserInfo userInfoQuery);

    /**
     *
     * @param userInfoQuery
     * @return
     */
    public UserInfo getUserInfo(UserInfo userInfoQuery);

    /**
     *
     * @param userInfoQuery
     */
    public void delete(UserInfo userInfoQuery);

    /**
     *
     * @param userInfo
     */
    public void addUserInfo(UserInfo userInfo);

    /**
     *
     * @param userInfo
     */
    public void updateUserInfo(UserInfo userInfo);
}
