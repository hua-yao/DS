package huayao.com.gmallmanageweb.controller;

import bean.SkuInfo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ManageService;

/**
 * @author huayao
 */
@Controller
public class SkuController {

    @Reference
    ManageService manageService;

    @PostMapping("saveSku")
    @ResponseBody
    public String saveSku(SkuInfo skuInfo){
        manageService.saveSkuInfo(skuInfo);
        return "success";
    }

    /**
     * 保存到es
     * @param skuId
     * @return
     */
    @PostMapping("onSale")
    @ResponseBody
    public String onSale(@RequestParam("skuId") String skuId){
        manageService.onSale(skuId);
        return "sesscon";
    }
}
