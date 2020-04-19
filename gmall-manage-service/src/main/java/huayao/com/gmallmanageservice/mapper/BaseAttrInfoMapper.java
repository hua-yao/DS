package huayao.com.gmallmanageservice.mapper;

import bean.BaseAttrInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @program: dainShangDemo
 * @description: 测试
 * @author: HuaYao
 * @create: 2020-01-08 15:54
 **/
public interface BaseAttrInfoMapper extends Mapper<BaseAttrInfo> {
    /**
     *
     * @param catalog3Id
     * @return
     */
    List<BaseAttrInfo> selectAttrInfoList(Long catalog3Id);
}
