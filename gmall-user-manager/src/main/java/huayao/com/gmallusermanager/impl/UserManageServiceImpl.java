package huayao.com.gmallusermanager.impl;

import bean.UserInfo;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import huayao.com.gmallusermanager.constant.Constant;
import huayao.com.gmallusermanager.mapper.UserInfoMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import redis.RedisUtil;
import redis.clients.jedis.Jedis;
import service.UserManageService;


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
    @Autowired
    RedisUtil redisUtil;


    /**
     * 核对后台
     * 1.传过来的密码是明文，但是数据库的密码是经过转换的，密码可以添加索引
     * 2.查询出来的数据要转换为json格式
     * 3.要保存到redis里面,setex key 生存时间 value
     * 4.返回数据
     * @param userInfo
     * @return
     */
    @Override
    public UserInfo login(UserInfo userInfo){
        //密码不能以明文存储在数据库里面
        String passWord = DigestUtils.md5Hex(userInfo.getPasswd());
        System.out.println(passWord);
        userInfo.setPasswd(passWord);
        UserInfo userInfoResult = null;
        //调用mapper.selectOne(T)的时候，我发现当T的属性值均为null的时候，这个方法会遍历整张表,在使用selectOne(T)查询的时候，推荐以索引的字段作为查询条件，并严格判断其值，若为null或空字符串时，抛出参数错误
        if (userInfo.getPasswd()!=null) {
             userInfoResult = userInfoMapper.selectOne(userInfo);
        }
        if (userInfoResult!=null){
            String userInfoKey = Constant.USER_KEY+ userInfoResult.getId()+Constant.INFO_KEY;
            Jedis jedis = redisUtil.getJedis();
            String userInfoJson = JSON.toJSONString(userInfoResult);
            //setex: key 生存时间 value
            jedis.setex(userInfoKey,3600,userInfoJson);
            return userInfoResult;
        }else{
            return null;
        }
    }

    @Override
    public boolean verify(String userId){
        Jedis jedis = redisUtil.getJedis();
        String userInfoKey = Constant.USER_KEY +userId+Constant.INFO_KEY;
        //查询是否有key
        Boolean exists = jedis.exists(userInfoKey);
        if (exists){
            //设置过期时间
            jedis.expire(userInfoKey, 1800);
        }
        return exists;
    }
}














































