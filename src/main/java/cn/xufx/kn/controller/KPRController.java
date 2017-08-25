package cn.xufx.kn.controller;
import cn.xufx.kn.domain.KnowledgePoint;
import cn.xufx.kn.service.KPService;
import cn.xufx.kn.service.KPRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
/**
 */
@Controller
public class KPRController
{
    @Autowired
    @Qualifier("kprService")
    private KPRService kprService;

    @Autowired
    @Qualifier("kpService")
    private KPService KPService;

    /*查询知识点的关系*/
    @RequestMapping(value = "/kpr/selectKPR")
    public String selectKPR(
            @RequestParam("id") Integer id,
            Model model
    )
    {
        KnowledgePoint kp= KPService.findKnowledgePointById(id);
        List<KnowledgePoint> pre_kps=kprService.selectPreKPS(id);
        List<KnowledgePoint> next_kps=kprService.selectNextKPS(id);
        List<KnowledgePoint> relate_kps=kprService.selectRelateKPS(id);

        model.addAttribute("kp",kp);
        model.addAttribute("pre_kps",pre_kps);
        model.addAttribute("next_kps",next_kps);
        model.addAttribute("relate_kps",relate_kps);
        return "kpr/kpr";
    }


    /*处理kpr.jsp传递过来的显示添加知识点关系请求*/
    @RequestMapping(value = "/kpr/showAddKPR")
    public String showAddKPR(Model model)
    {
        List<KnowledgePoint> kps= KPService.findAllKP();
        model.addAttribute("kps",kps);
        return "kpr/showAddKPR";
    }

    /*查询知识点的所有关系*/
    @RequestMapping(value = "/kpr/searchRelations")
    @ResponseBody
    public List<Boolean> searchRelation(Integer firstID, Integer relation,Integer secondID )
    {
        List<Boolean> list=kprService.haveRelations(firstID, relation,secondID);
        return  list;
    }
    /*添加知识点关系*/
    @RequestMapping(value = "/kpr/addRelations")
    public ModelAndView addRelations(Integer firstID, Integer relation, Integer secondID,ModelAndView mv)
    {
        kprService.addRelation(firstID,relation,secondID);
        mv.setViewName("redirect:/kp/selectKP");
        return mv;
    }
/*
    @RequestMapping(value = "/kpr/selectAllKPR")
    String selectAllKPR(Model model)
    {
        List<KnowledgePoint> kps= KPService.findAllKP();
        List<List<KnowledgePoint>> allRelateKPS=new ArrayList<>();
        Map<KnowledgePoint,List<List<KnowledgePoint>>> kps_allRelateKPS=new HashMap<>();
        for (KnowledgePoint kp:kps)
        {
            Integer id=kp.getId();
            List<KnowledgePoint> pre_kps=kprService.selectPreKPS(id);
            List<KnowledgePoint> next_kps=kprService.selectNextKPS(id);
            List<KnowledgePoint> relate_kps=kprService.selectRelateKPS(id);
            allRelateKPS.add(pre_kps);
            allRelateKPS.add(next_kps);
            allRelateKPS.add(relate_kps);
            *//*知识点及其关联知识点*//*
            kps_allRelateKPS.put(kp,allRelateKPS);
        }

        model.addAttribute("kps_allRelateKPS",kps_allRelateKPS);
        return "kpr/allKPR";
    }*/

    /*删除前续知识点
    * kpID:知识点
    * ids：前续知识点集合*/
    @RequestMapping(value = "/kp/removePreKP")
    ModelAndView deletePreKP(String ids,Integer kpID,ModelAndView mv)
    {
        kprService.deletePreKPS(ids,kpID);
        mv.setViewName("redirect:/kp/selectKP");
        return mv;
    }
    /*删除后续知识点
    * kpID:知识点
    * ids：后续知识点集合*/
    @RequestMapping(value = "/kp/removeNextKP")
    ModelAndView deleteNextKP(Integer kpID,String ids,ModelAndView mv)
    {
        kprService.deleteNextKPS(kpID,ids);
        mv.setViewName("redirect:/kp/selectKP");
        return mv;
    }

    /*删除相关知识点
    * kpID:知识点
    * ids：相关知识点集合*/
    @RequestMapping(value = "/kp/removeRelateKP")
    ModelAndView deleteRelateKP(Integer kpID,String ids,ModelAndView mv)
    {
        kprService.deleteRelateKPS(kpID,ids);
        mv.setViewName("redirect:/kp/selectKP");
        return mv;
    }
}
