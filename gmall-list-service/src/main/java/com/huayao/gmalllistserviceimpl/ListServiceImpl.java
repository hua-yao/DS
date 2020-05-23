package com.huayao.gmalllistserviceimpl;

import bean.SkuLsInfo;
import com.alibaba.dubbo.config.annotation.Service;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.springframework.beans.factory.annotation.Autowired;
import service.ListService;

import java.io.IOException;

/**
 * @author huayao
 */
@Service
public class ListServiceImpl implements ListService {

    @Autowired
    JestClient jestClient;

    /**
     * es保存数据
     * @param skuLsInfo
     */
    @Override
    public void saveSkuInfoEs(SkuLsInfo skuLsInfo) {
        Index index = new Index.Builder(skuLsInfo).index("gmall").type("skuInfo").id(skuLsInfo.getId()).build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

































