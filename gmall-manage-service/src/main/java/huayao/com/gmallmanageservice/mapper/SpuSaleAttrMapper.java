package huayao.com.gmallmanageservice.mapper;

import bean.SpuSaleAttr;
import bean.SpuSaleAttrValue;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 当参数有两个的时候需要定义@Param
     * @param skuId
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> selectSaleAttrInfoListBySku(@Param("skuId") Long skuId, @Param("spuId")  Long spuId);
}
