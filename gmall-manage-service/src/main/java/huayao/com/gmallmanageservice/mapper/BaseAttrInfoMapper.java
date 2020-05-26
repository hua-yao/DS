package huayao.com.gmallmanageservice.mapper;

import bean.BaseAttrInfo;
import org.apache.ibatis.annotations.Param;
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
     *查询平台属性
     * @param catalog3Id
     * @return
     */
    List<BaseAttrInfo> selectAttrInfoList(Long catalog3Id);

    /**
     * 查询平台属性和平台属性值
     * @param valueId
     * @return
     */
    List<BaseAttrInfo> selectAttrInfoValueId(@Param("valueId")String valueId);
}
