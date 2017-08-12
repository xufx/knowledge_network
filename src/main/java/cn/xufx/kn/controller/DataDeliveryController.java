package cn.xufx.kn.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Created by xufuxiu on 2017/8/11.
 */
@Controller
public class DataDeliveryController
{
    @RequestMapping("/data/listDelivery")
    @ResponseBody
    public void  deleteCatalogSchemes(@RequestParam("idList[]")List<String>idList,Boolean isBatch)
    {
        System.out.println("idList:"+idList);
    }
}
