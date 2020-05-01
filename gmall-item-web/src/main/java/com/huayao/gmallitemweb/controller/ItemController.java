package com.huayao.gmallitemweb.controller;

import bean.SkuInfo;
import bean.SkuSaleAttrValue;
import bean.SpuSaleAttr;
import bean.SpuSaleAttrValue;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ManageService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huayao
 */
@Controller
public class ItemController {

    @Reference
    ManageService manageService;

    @GetMapping("{skuId}.html")
    public String getItem(@PathVariable("skuId")Long skuId, HttpServletRequest request){
        SkuInfo sKuInfo = manageService.getSKuInfo(skuId);
        //对象域，前端可以同通过这个对象域取值
        request.setAttribute("skuInfo",sKuInfo);
        List<SpuSaleAttr> saleAttrList = manageService.selectSaleAttrInfoListBySku(String.valueOf(sKuInfo.getSpuId()), String.valueOf(skuId));
        List<SkuSaleAttrValue> skuSaleAttrValueListBySpu = manageService.getSkuSaleAttrValueListBySpu(String.valueOf(sKuInfo.getSpuId()));
        /**
         * 1。定义一个字符串
         * 2.定义一个map结构
         * 3.第一次进来没有值
         * 4.取值付值给定义好的字符串
         * 5.有两种情况。1）。最后一个下标了，这个时候可以直接拼接了 2）。相同sku的时候表示已经拼接好了，可以付值给map结构了
         */
        String valueIdString = "";
        Map valueIdsSkuIdMap = new HashMap<>(skuSaleAttrValueListBySpu.size());
        for (int i = 0; i < skuSaleAttrValueListBySpu.size(); i++) {
            //有值就进行拼接
            if (valueIdString.length()>0){
                valueIdString+="|";
            }
            //取值
            SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueListBySpu.get(i);
            valueIdString+=skuSaleAttrValue.getSaleAttrValueId();
            //判断是否下标越界，判断是是同一个sku
            if ((i+1)<skuSaleAttrValueListBySpu.size()){
                SkuSaleAttrValue skuSaleAttrValueNext = skuSaleAttrValueListBySpu.get(i + 1);
                if (!skuSaleAttrValueNext.getSkuId().equals(skuSaleAttrValue.getSkuId())){
                    valueIdsSkuIdMap.put(valueIdString,skuSaleAttrValue.getSkuId());
                    valueIdString = "";
                }
            }else {
                valueIdsSkuIdMap.put(valueIdString,skuSaleAttrValue.getSkuId());
                valueIdString = "";
            }
        }
        //前台不能接收map结构，需要序列化成字符串
        String valueIdSkuIdJson = JSON.toJSONString(valueIdsSkuIdMap);
        request.setAttribute("valueIdSkuIdJson",valueIdSkuIdJson);
        request.setAttribute("saleAttrList",saleAttrList);
        return "item";
    }
}





























