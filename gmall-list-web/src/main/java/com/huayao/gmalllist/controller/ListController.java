package com.huayao.gmalllist.controller;

import bean.*;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ListService;
import service.ManageService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class ListController {
    @Reference
    ListService listService;
    @Reference
    ManageService manageService;

    @GetMapping("list")
    public String list(SkuLsParams skuLsParams, HttpServletRequest request) {
        if (skuLsParams.getValueId()!=null){
            skuLsParams.changeArray2List();
        }
        SkuLsResult search = listService.search(skuLsParams);
        List<String> attrValueIdList = search.getAttrValueIdList();
        request.setAttribute("skuLsInfo", search.getSkuLsInfoList());
        List<BaseAttrInfo> attrInfoListResult = new ArrayList<>();
        if (search.getAttrValueIdList()!=null&&search.getAttrValueIdList().size()>0) {
            attrInfoListResult = manageService.getPropertyValue(attrValueIdList);
        }

        /**
         * 定义一个bean
         * 封装平台属性名称和平台属性值
         */
        List<BaseAttrValueEx> selectedValueList = new ArrayList<>();

        //点击属性的时候，要删除这一行数据，是在变量里面删除就是内存里面删除
        List<String> valueIdList = skuLsParams.getValueIdList();
        for (String valueIdSelected : valueIdList) {
            for (Iterator<BaseAttrInfo> iterator = attrInfoListResult.iterator(); iterator.hasNext(); ) {
                BaseAttrInfo baseAttrInfoResult = iterator.next();
                List<BaseAttrValue> attrValueListRs = baseAttrInfoResult.getAttrValueList();
                for (BaseAttrValue baseAttrValue : attrValueListRs) {
                    if(String.valueOf(baseAttrValue.getId()).equals(valueIdSelected)){
                        BaseAttrValueEx baseAttrValueEx = new BaseAttrValueEx();
                        baseAttrValueEx.setWholeName(baseAttrInfoResult.getAttrName()+":"+baseAttrValue.getValueName());
                        baseAttrValueEx.setCancelUrlParam(makeUrlParam(skuLsParams,valueIdSelected));
                        System.out.println("CancelUrlParam()=" + baseAttrValueEx.getCancelUrlParam());
                        selectedValueList.add(baseAttrValueEx);
                        iterator.remove();
                    }
                }

            }
        }
        request.setAttribute("selectedValueList",selectedValueList);
        request.setAttribute("baseAttrInfo",attrInfoListResult);
        request.setAttribute("urlParam",makeUrlParam(skuLsParams));
        request.setAttribute("keyword",skuLsParams.getKeyword());
        request.setAttribute("pageNo",skuLsParams.getPageNo());
        request.setAttribute("totalPages",search.getTotalPages());
        return "list";
    }

    /**
     * 拼接url
     * String ... cancelValueId 不给参数也可以用,它的作用是从url路径中去掉某个值
     * @param skuLsParams
     * @return
     */
    public String makeUrlParam(SkuLsParams skuLsParams,String ... cancelValueId){
        String urlParam = "";
        if (skuLsParams.getKeyword()!=null){
            urlParam="keyword=" + skuLsParams.getKeyword();
        }
        if (skuLsParams.getCatalog3Id()!=null){
            if (urlParam.length()>0){
                urlParam+="&";
            }
            urlParam+="catalog3Id=" + skuLsParams.getCatalog3Id();
        }
        if (skuLsParams.getValueId()!=null&&skuLsParams.getValueId().length>0){
            for (int i = 0; i < skuLsParams.getValueId().length; i++) {
                String valueId = skuLsParams.getValueId()[i];
                if (cancelValueId!=null&&cancelValueId.length==1){
                    if (valueId.equals(cancelValueId[0])){
                        continue;
                    }
                }
                if (urlParam.length()>0){
                    urlParam+="&";
                }
                urlParam+="valueId=" + valueId;
            }
        }
        return urlParam;
    }


}

























