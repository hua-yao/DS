package service;

import bean.SkuLsInfo;

/**
 * @author huayao
 */
public interface ListService {


    /**
     * 保存到es
     * @param skuLsInfo
     */
    public void saveSkuInfoEs(SkuLsInfo skuLsInfo);
}
