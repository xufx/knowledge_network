package cn.xufx.kn.controller;
import cn.xufx.kn.domain.KnowledgePoint;
import cn.xufx.kn.service.KNService;
import cn.xufx.kn.service.KPRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
/**
 * Created by xiao on 2017/2/27.
 */
@Controller
public class KPRelativeController
{
    @Autowired
    @Qualifier("kprService")
    private KPRelativeService kprService;

    @Autowired
    @Qualifier("knService")
    private KNService knService;

    @RequestMapping(value = "/kpr/selectKPR")
    public String selectKPR(
            @RequestParam("id") Integer id,
            Model model
    )
    {
        KnowledgePoint kp=knService.findKnowledgePointById(id);
        List<KnowledgePoint> pre_kps=kprService.selectPreKPS(id);
        List<KnowledgePoint> next_kps=kprService.selectNextKPS(id);
        List<KnowledgePoint> relate_kps=kprService.selectRelateKPS(id);

        model.addAttribute("kp",kp);
        model.addAttribute("pre_kps",pre_kps);
        model.addAttribute("next_kps",next_kps);
        model.addAttribute("relate_kps",relate_kps);
        return "kpr/kpr";
    }

    /*处理添加知识点前后关系的请求*/
    @RequestMapping(value = "/kpr/addPreNext")
    public ModelAndView addPreNext(Map<Integer,Integer> params,ModelAndView mv)
    {
        if (params.size()!=0)
        {
            kprService.insertPreAndNext(params);
        }
        mv.setViewName("redirect:/kp/selectKP");//重定向为查询请求
        return mv;
    }

}
