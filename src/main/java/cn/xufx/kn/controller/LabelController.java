package cn.xufx.kn.controller;
import cn.xufx.kn.domain.Label;
import cn.xufx.kn.service.LabelService;
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
public class LabelController
{
    @Autowired
    @Qualifier("labelService")
    private LabelService labelService;

    @RequestMapping(value = "/label/selectLabel")
    public String selectLabel(
            Integer pageIndex,//请求的是第几页
            String doc,//职位编号
            String video,//部门编号
            @ModelAttribute Label label,//模糊查询参数
            Model model
            )
    {
        /*模糊查询时判断是否有关联对象传递，如果有，创建并封装关联对象*/
        PageModel pageModel=new PageModel();
        if (pageIndex!=null)
            pageModel.setPageIndex(pageIndex);//显示第pageIndex页

        List<Label> labels=labelService.findLabel(label,pageModel);

        model.addAttribute("labels",labels);
        model.addAttribute("pageModel",pageModel);
        return "label/label";
    }

    /*处理添加员工请求*/
    @RequestMapping(value = "/label/addLabel")
    public ModelAndView addLabel(
            String flag,//1表示跳转到添加页面，2表示执行添加操作
            String doc,
            String video,
            @ModelAttribute Label label,
            ModelAndView mv
    )
    {
        if (flag.equals("1"))
        {  //先查询跳转到添加的界面
            mv.setViewName("label/showAddLabel");
        }
        else
        {
            System.out.println("LabelController 要添加的对象："+label);
            labelService.addLabel(label);//添加知识点
            mv.setViewName("redirect:/label/selectLabel");//重定向为查询请求
        }
        return mv;
    }
    /*处理删除请求*/
    @RequestMapping(value = "/label/removeLabel")
    public ModelAndView removeLabel(
            String ids,
            ModelAndView mv
    )
    {
        String[] idArray=ids.split(",");
        for(String id:idArray)
        {
            labelService.removeLabelById(Integer.parseInt(id));
        }
        mv.setView(new RedirectView("/label/selectLabel"));
        mv.setViewName("forward:/label/selectLabel");
        mv.setViewName("redirect:/label/selectLabel");
        return mv;
    }
    /*处理修改请求*/
    @RequestMapping(value = "/label/updateLabel")
    public ModelAndView  updateLabel(
            String flag,
            String doc,
            String video,
            @ModelAttribute Label label,
            ModelAndView mv
    )
    {
        if (flag.equals("1"))
        {
            Label target=labelService.findLabelById(label.getId());
            System.out.println("修改之前的Label对象："+label);
            mv.addObject("label",target);
            mv.setViewName("label/showUpdateLabel");
        }else
        {
            System.out.println("修改之后的Label对象："+label);
            labelService.modifyLabel(label);
            mv.setViewName("redirect:/label/selectLabel");
        }
        return mv;
    }

}
