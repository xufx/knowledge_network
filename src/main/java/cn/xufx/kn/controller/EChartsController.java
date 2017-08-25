package cn.xufx.kn.controller;
import cn.xufx.kn.util.echarts.EChartsProduct;
import cn.xufx.kn.util.echarts.EChartsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**EChartsController主要负责处理前端页面发送的与ECharts渲染有关的HTTP请求，并通过调用EChartsService服务生成符合ECharts数据格式规范的JSON数据
 */
@Controller
@RequestMapping("/echarts")
public class EChartsController
{

    @Resource
    private EChartsService echartsService;

    /*处理查询知识网络可视化的请求*/
    @RequestMapping(value = "/knowledge-point",method = RequestMethod.POST)
    @ResponseBody
    public EChartsProduct showKnowledgeGraph(Integer id,Integer level,Boolean spread)throws Exception
    {
        System.out.println("EchartsController:id="+id+",level="+level+",spread="+spread);
        return echartsService.build(id, level, spread);
    }

}
