package com.huayao.gmalllistserviceimpl;

import bean.SkuLsInfo;
import bean.SkuLsParams;
import bean.SkuLsResult;
import com.alibaba.dubbo.config.annotation.Service;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.TermsAggregation;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import redis.RedisUtil;
import redis.clients.jedis.Jedis;
import service.ListService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huayao
 */
@Service
public class ListServiceImpl implements ListService {

    @Autowired
    JestClient jestClient;

    @Autowired
    RedisUtil redisUtil;
    /**
     * es保存数据
     *
     * @param skuLsInfo
     */
    @Override
    public void saveSkuInfoEs(SkuLsInfo skuLsInfo) {
        Index index = new Index.Builder(skuLsInfo).index("gmall").type("skuInfo").id(String.valueOf(skuLsInfo.getId())).build();
        try {
            DocumentResult execute = jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * es查询数据
     *
     * @param
     * @return
     */

    @Override
    public SkuLsResult search(SkuLsParams skuLsParams) {
        String query = makeQueryStringForSearch(skuLsParams);
        SearchResult searchResult = null;
        Search search = new Search.Builder(query).addIndex("gmall").addType("skuInfo").build();
        try {
            searchResult = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SkuLsResult skuLsResult = makeResultForSearch(skuLsParams, searchResult);
        return skuLsResult;
    }

    /**
     * 查询
     *
     * @param skuLsParams
     * @return
     */
    private String makeQueryStringForSearch(SkuLsParams skuLsParams) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (skuLsParams.getCatalog3Id() != null) {
            TermsQueryBuilder termsQueryBuilder = new TermsQueryBuilder("catalog3Id", skuLsParams.getCatalog3Id());
            boolQueryBuilder.filter(termsQueryBuilder);
        }
        if (skuLsParams.getValueId() != null) {
            TermsQueryBuilder termsQueryBuilder1 = new TermsQueryBuilder("skuAttrValueList.valueId", skuLsParams.getValueId());
            boolQueryBuilder.filter(termsQueryBuilder1);
        }
        if (skuLsParams.getKeyword() != null) {
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("skuName", skuLsParams.getKeyword());
            boolQueryBuilder.must(matchQueryBuilder);
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuName");
            highlightBuilder.preTags("<span style='color:red'>");
            highlightBuilder.postTags("</span>");
            searchSourceBuilder.highlight(highlightBuilder);
        }
        searchSourceBuilder.query(boolQueryBuilder);

        searchSourceBuilder.size(skuLsParams.getPageSize());
        int from = (skuLsParams.getPageNo() - 1) * skuLsParams.getPageSize();
        searchSourceBuilder.from(from);

        searchSourceBuilder.sort("hotScore", SortOrder.DESC);

        TermsBuilder termsBuilder = AggregationBuilders.terms("groupby_attr").field("skuAttrValueList.valueId");
        searchSourceBuilder.aggregation(termsBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println("query = " + query);
        return query;
    }

    /**
     * 取值封装到bean
     *
     * @param skuLsParams
     * @param searchResult
     * @return
     */
    private SkuLsResult makeResultForSearch(SkuLsParams skuLsParams, SearchResult searchResult) {
        SkuLsResult skuLsResult = new SkuLsResult();
        //获取商品列表
        List<SearchResult.Hit<SkuLsInfo, Void>> hits = searchResult.getHits(SkuLsInfo.class);
        List<SkuLsInfo> skuLsInfoList = new ArrayList<>(hits.size());
        for (SearchResult.Hit<SkuLsInfo, Void> hit : hits) {
            SkuLsInfo skuInfos = hit.source;
            try {
                List<String> skuName = hit.highlight.get("skuName");
                skuInfos.setSkuName(skuName.get(0));
            } catch (Exception e) {
            }
            skuLsInfoList.add(skuInfos);
        }
        System.out.println("skuLsInfoList = " + skuLsInfoList);
        skuLsResult.setSkuLsInfoList(skuLsInfoList);
        //获取平台属性值列表
        MetricAggregation aggregations = searchResult.getAggregations();
        TermsAggregation groupby_valueId = aggregations.getTermsAggregation("groupby_attr");

        List<TermsAggregation.Entry> buckets = groupby_valueId.getBuckets();
        List<String> attrVlaueList = new ArrayList<>(buckets.size());
        for (TermsAggregation.Entry bucket : buckets) {
            String attrVlaueId = bucket.getKey();
            attrVlaueList.add(attrVlaueId);
        }
        skuLsResult.setAttrValueIdList(attrVlaueList);
        Long total = searchResult.getTotal();
        skuLsResult.setTotal(total);
        //总的页码
        Long totalPage = (total + skuLsParams.getPageSize() - 1) / skuLsParams.getPageSize();
        skuLsResult.setTotalPages(totalPage);
        return skuLsResult;
    }

    @Override
    public void incrHotScore(String skuId){
        Jedis jedis = redisUtil.getJedis();
        String key = "hotScore";
        Double hotScore = jedis.zincrby(key, 1, skuId);
        //当hotScore是100的时候就会更新热度评分
        int a = 10;
        if (hotScore%a==0){
            updateHotScore(skuId,hotScore.longValue());
        }
    }

    /**
     * 更新热度评分
     * @param skuId
     * @param hotScore
     */
    private void updateHotScore(String skuId,Long hotScore){
        String updateJson="{\n" +
                "   \"doc\":{\n" +
                "     \"hotScore\":"+hotScore+"\n" +
                "   }\n" +
                "}";
        Update update = new Update.Builder(updateJson).index("gmall").type("skuInfo").id(skuId).build();
        try {
            jestClient.execute(update);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

































