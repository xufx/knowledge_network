package cn.xufx.kn.controller;
import cn.xufx.kn.domain.KnowledgePoint;
import cn.xufx.kn.service.KPService;
import cn.xufx.kn.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by xufuxiu on 2017/7/26.
 */
@Controller
public class KPController
{
    @Autowired
    @Qualifier("kpService")
    private KPService KPService;

    @RequestMapping(value = "/kp/selectKP")
    public String selectKnowledgePoint(
            Integer pageIndex,//请求的是第几页
            String doc,//职位编号
            String video,//部门编号
            @ModelAttribute KnowledgePoint kp,//模糊查询参数
            Model model
            )
    {
        PageModel pageModel=new PageModel();
        if (pageIndex!=null)
            pageModel.setPageIndex(pageIndex);//显示第pageIndex页

        List<KnowledgePoint> kps= KPService.findKnowledgePoint(kp,pageModel);

        List<Integer>ids=new ArrayList<>();
        for(KnowledgePoint k:kps)
        {
            ids.add(k.getId());
        }
        model.addAttribute("kps",kps);
        model.addAttribute("pageModel",pageModel);
        return "kp/kp";
    }

    /*处理添加知识点请求*/
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
            KPService.addKnowledgePoint(kp);//添加知识点
            mv.setViewName("redirect:/kp/selectKP");//重定向为查询请求
        }
        return mv;
    }
    /*处理删除知识点请求*/
    @RequestMapping(value = "/kp/removeKP")
    public ModelAndView removeKnowledgePoint(
            String ids,
            ModelAndView mv
    )
    {
        String[] idArray=ids.split(",");
        for(String id:idArray)
        {
            KPService.removeKnowledgePointById(Integer.parseInt(id));
        }
        mv.setView(new RedirectView("/kp/selectKP"));
        mv.setViewName("forward:/kp/selectKP");
        mv.setViewName("redirect:/kp/selectKP");
        return mv;
    }
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
            KnowledgePoint target= KPService.findKnowledgePointById(kp.getId());
            System.out.println("修改之前的KnowledgePoint对象："+kp);
            mv.addObject("kp",target);
            mv.setViewName("kp/showUpdateKP");
        }else
        {
            System.out.println("修改之后的KnowledgePoint对象："+kp);
            KPService.modifyKnowledgePoint(kp);
            mv.setViewName("redirect:/kp/selectKP");
        }
        return mv;
    }



/*点击输入框，出现知识点进行选择*/
    @RequestMapping(value = "/kp/selectAllKP",method = RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE)
    public void searchAllKP(ModelAndView mv)
    {
        List<KnowledgePoint> kps= KPService.findAllKP();
        mv.addObject("kps",kps);
    }


    /*知识点添加界面的知识点查询*/
    @RequestMapping(value = "/kp/selectKPByName",method = RequestMethod.POST)
    @ResponseBody
    public Boolean selectKPByName(String name)
    {
        KnowledgePoint kp= KPService.selectKPByName(name);
        Boolean flag=false;
        if (kp!=null)
        {
            flag=true;
        }
        return flag;
    }
}
