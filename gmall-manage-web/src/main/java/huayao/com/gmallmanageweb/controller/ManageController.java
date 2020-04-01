package huayao.com.gmallmanageweb.controller;

import bean.BaseCatalog1;
import bean.BaseCatalog2;
import bean.BaseCatalog3;
import bean.BaseSaleAttr;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ManageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: dainShangDemo
 * @description: 控制器
 * @author: HuaYao
 * @create: 2020-01-02 22:25
 **/
@Controller
public class ManageController {

    @Reference
    ManageService manageService;

    @RequestMapping(value = "index" )
    public String index(){
        return "index";
    }

    @GetMapping("catalog1List")
    @ResponseBody //不加这个注解会将结果当成页面进行解析，所以会报错
    public String getCatalog1List(){
        List<BaseCatalog1> catalog1List = manageService.getCatalog1List();
        //转换JSON格式
        String jsonCatalog1List = JSON.toJSONString(catalog1List);
        return jsonCatalog1List;
    }

    @GetMapping("catalog2List")
    @ResponseBody
    public String getCatalog2List(HttpServletRequest request){
        String catalog1Id = request.getParameter("catalog1Id");
        List<BaseCatalog2> catalog2List = manageService.getCatalog2List(catalog1Id);
        String jsonCatalog2List = JSON.toJSONString(catalog2List);
        return jsonCatalog2List;
    }

    @GetMapping("catalog3List")
    @ResponseBody
    public String getCatalog3List(HttpServletRequest request){
        String catalog1Id = request.getParameter("catalog2Id");
        List<BaseCatalog3> catalog3List = manageService.getCatalog3List(catalog1Id);
        String jsonCatalog2List = JSON.toJSONString(catalog3List);
        return jsonCatalog2List;
    }

    @GetMapping("baseSaleAttrList")
    @ResponseBody
    public List<BaseSaleAttr> getBaseSaleAttr(){
        List<BaseSaleAttr> baseSaleAttrList = manageService.getBaseSaleAttrList();
        return baseSaleAttrList;
    }



}







































