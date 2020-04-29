package huayao.com.gmallmanageservice.mapper;

import bean.SpuSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author huayao
 */
public interface SpuSaleAttrMapper extends Mapper<SpuSaleAttr> {
    /**
     * 查询销售属性
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> selectSaleAttrInfoList(Long spuId);
}
