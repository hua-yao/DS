package huayao.com.gmallusermanager.impl;

import bean.UserInfo;
import com.alibaba.dubbo.config.annotation.Service;
import huayao.com.gmallusermanager.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserManageService;

import java.util.List;

/**
 * @program: dainShangDemo
 * @description: 接口实现类
 * @author: HuaYao
 * @create: 2020-01-02 17:19
 **/
@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    UserInfoMapper userInfoMapper;

    //查询所有
    @Override
    public List<UserInfo> getUserInfoList(UserInfo userInfoQuery){
        List<UserInfo> userInfos=null;
        //查询所有
//        userInfos = userInfoMapper.selectAll();
        //条件匹配查询
        userInfos =userInfoMapper.select(userInfoQuery);
        //特殊条件匹配查询 比如：按姓氏匹配
//        Example example=new Example(UserInfo.class);
//        example.createCriteria().andLike("loginName","%"+userInfoQuery.getLoginName()+"%");
//        userInfos = userInfoMapper.selectByExample(example);
        return userInfos;
    }


    @Override
    public UserInfo getUserInfo(UserInfo userInfoQuery) {
        return null;
    }

    @Override
    public void delete(UserInfo userInfoQuery) {

    }

    @Override
    public void addUserInfo(UserInfo userInfo) {

    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {

    }
}
