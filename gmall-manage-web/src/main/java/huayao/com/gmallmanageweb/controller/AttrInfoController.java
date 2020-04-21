package huayao.com.gmallmanageweb.controller;

import bean.BaseAttrInfo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
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
 * @create: 2020-01-03 11:07
 **/
@Controller
public class AttrInfoController {

    @Reference
    ManageService manageService;

    @GetMapping("attrListPage")
    public String AttrListpage(){
        return "attrListPage";
    }


    @GetMapping("attrList")
    @ResponseBody
    public String attrList(HttpServletRequest request){
        String catalog1Id = request.getParameter("catalog3Id");
        List<BaseAttrInfo> baseAttrInfos = manageService.attList(catalog1Id);
        String baseAttrInfoList = JSON.toJSONString(baseAttrInfos);
        return baseAttrInfoList;
    }

    @GetMapping("attrListForSku")
    @ResponseBody
    public List<BaseAttrInfo> attrListForSku(HttpServletRequest request){
        String catalog1Id = request.getParameter("catalog3Id");
        List<BaseAttrInfo> baseAttrInfos = manageService.attList(catalog1Id);
        return baseAttrInfos;
    }

    @PostMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(BaseAttrInfo baseAttrInfo){
        manageService.saveAttrInfo(baseAttrInfo);
        return "success";
    }

}
