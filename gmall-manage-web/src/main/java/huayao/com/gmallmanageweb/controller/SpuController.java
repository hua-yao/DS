package huayao.com.gmallmanageweb.controller;

import bean.SpuImage;
import bean.SpuInfo;
import bean.SpuSaleAttr;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ManageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: dainShangDemo
 * @description: 测试
 * @author: HuaYao
 * @create: 2020-01-06 20:36
 **/
@Controller
public class SpuController {

    @Reference
    ManageService manageService;

    @GetMapping("spuListPage")
    public String spuListPage(){
        return "spuListPage";
    }

    @GetMapping("spuList")
    @ResponseBody
    public String spuList(HttpServletRequest request){
        String catalog3Id = request.getParameter("catalog3Id");
        List<SpuInfo> spuInfoList =  manageService.getSpuList(catalog3Id);
        String spuJons = JSON.toJSONString(spuInfoList);
        return spuJons;
    }

    @PostMapping("saveSpuInfo")
    @ResponseBody
    public ResponseEntity<Void> saveSpuInfo(SpuInfo spuInfo){
        manageService.saveSpuInfo(spuInfo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("saleAttrListForSku")
    @ResponseBody
    public List<SpuSaleAttr> getSaleAttrList(HttpServletRequest request ){
        String spuId = request.getParameter("spuId");
        List<SpuSaleAttr> saleAttrList = manageService.getSaleAttrList(spuId);
        return saleAttrList;
    }

    @GetMapping("spuImageList")
    @ResponseBody
    public List<SpuImage> getSpuImageList(HttpServletRequest request ){
        String spuId = request.getParameter("spuId");
        List<SpuImage> spuImageList = manageService.getSpuImageList(spuId);
        return spuImageList;
    }
}




























