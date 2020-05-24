package service;

import bean.SkuLsInfo;
import bean.SkuLsParams;
import bean.SkuLsResult;

import javax.naming.directory.SearchResult;

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
}
