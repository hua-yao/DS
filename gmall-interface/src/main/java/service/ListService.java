package service;

import bean.SkuLsInfo;
import bean.SkuLsParams;
import bean.SkuLsResult;


/**
 * @author huayao
 */
public interface ListService {


    /**
     * 保存到es
     * @param skuLsInfo
     */
    public void saveSkuInfoEs(SkuLsInfo skuLsInfo);

    /**
     * es查询数据
     * @return
     */
    public SkuLsResult search(SkuLsParams skuLsParams);

    /**
     * 计数器更新热度评分
     * @param skuId
     */
    public void incrHotScore(String skuId);
}
