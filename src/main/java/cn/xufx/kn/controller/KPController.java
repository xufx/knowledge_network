package cn.xufx.kn.controller;
import cn.xufx.kn.domain.*;
import cn.xufx.kn.service.KNService;
import cn.xufx.kn.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
/**
 * Created by xufuxiu on 2017/7/26.
 */
@Controller
public class KPController
{
    @Autowired
    @Qualifier("knService")
    private KNService knService;

    @RequestMapping(value = "/kp/selectKP")
    public String selectKnowledgePoint(
            Integer pageIndex,//请求的是第几页
            String doc,//职位编号
            String video,//部门编号
            @ModelAttribute KnowledgePoint kp,//模糊查询参数
            Model model
            )
    {
        /*模糊查询时判断是否有关联对象传递，如果有，创建并封装关联对象*/
        PageModel pageModel=new PageModel();
        if (pageIndex!=null)
            pageModel.setPageIndex(pageIndex);//显示第pageIndex也

        List<KnowledgePoint> kps=knService.findKnowledgePoint(kp,pageModel);

        model.addAttribute("kps",kps);
        model.addAttribute("pageModel",pageModel);
        return "kp/kp";
    }

    /*处理添加员工请求*/
    @RequestMapping(value = "/kp/addKP")
    public ModelAndView addKnowledgePoint(
            String flag,//1表示跳转到添加页面，2表示执行添加操作
            String doc,
            String video,
            @ModelAttribute KnowledgePoint kp,
            ModelAndView mv
    )
    {
        if (flag.equals("1"))
        {  //先查询跳转到添加的界面
            mv.setViewName("kp/showAddKP");
        }
        else
        {//判断是否有关联对象，如果有则创建
            System.out.println("KnowledgePointController 要添加的对象："+kp);
            knService.addKnowledgePoint(kp);//添加知识点
            mv.setViewName("redirect:/kp/selectKP");//重定向为查询请求
        }
        return mv;
    }
    /*处理删除员工请求*/
    @RequestMapping(value = "/kp/removeKP")
    public ModelAndView removeKnowledgePoint(
            String ids,
            ModelAndView mv
    )
    {
        String[] idArray=ids.split(",");
        for(String id:idArray)
        {
            knService.removeKnowledgePointById(Integer.parseInt(id));
        }
        mv.setView(new RedirectView("/kp/selectKP"));
        mv.setViewName("forward:/kp/selectKP");
        mv.setViewName("redirect:/kp/selectKP");
        return mv;
    }
    /*处理修改员工请求*/
    @RequestMapping(value = "/kp/updateKP")
    public ModelAndView  updateKnowledgePoint(
            String flag,
            String doc,
            String video,
            @ModelAttribute KnowledgePoint kp,
            ModelAndView mv
    )
    {
        if (flag.equals("1"))
        {
            KnowledgePoint target=knService.findKnowledgePointById(kp.getId());
            System.out.println("修改之前的KnowledgePoint对象："+kp);
            mv.addObject("kp",target);
            mv.setViewName("kp/showUpdateKP");
        }else
        {
            System.out.println("修改之后的KnowledgePoint对象："+kp);
            knService.modifyKnowledgePoint(kp);
            mv.setViewName("redirect:/kp/selectKP");
        }
        return mv;
    }

}
