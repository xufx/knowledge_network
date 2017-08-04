package cn.xufx.kn.controller;
import cn.xufx.kn.domain.User;
import cn.xufx.kn.service.KNService;
import cn.xufx.kn.util.common.KnConstants;
import cn.xufx.kn.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * Created by xufuxiu on 2017/7/18.
 */
@Controller
public class UserController
{
    @Autowired
    @Qualifier("knService")
    private KNService knService;

    @RequestMapping(value = "/login")
    public ModelAndView login(//获得前端jsp页面传递过来的参数
            @RequestParam("loginname") String loginname,
            @RequestParam("password") String password,
            HttpSession session,
            ModelAndView mv)
    {
        System.out.println("UserController");
        User user=knService.login(loginname,password);
        if (user!=null)
        {/*将用户保存到HttpSession中*/
            session.setAttribute(KnConstants.USER_SESSION,user);//然后进入AuthorizedInterceptor.preHandle()
            mv.setViewName("redirect:/main");//客户端跳转到main.jsp
        }else
        {
            mv.addObject("message","登录名或密码错误！请重新输入");
            mv.setViewName("forward:/loginForm");//服务器内部跳转到登陆页面
        }
        return mv;
    }

    /*处理查询请求
    * @param pageIndex请求的是第几页
    * @param user模糊查询参数
    * @param model
    * */
    @RequestMapping(value="/user/selectUser")
    public String selectUser(Integer pageIndex, @ModelAttribute User user,Model model)
    {System.out.println(" selectUser()");
        System.out.println("user="+user);
        PageModel pageModel=new PageModel();
        if (pageIndex!=null)pageModel.setPageIndex(pageIndex);
        /*查询用户信息*/
        List<User> users=knService.findUser(user,pageModel);
        model.addAttribute("users",users);
        model.addAttribute("pageModel",pageModel);
        return "user/user";
    }
    /*处理删除用户请求*/
    @RequestMapping(value = "/user/removeUser")
    public ModelAndView removeUser(String ids,ModelAndView mv)
    {System.out.println("enterinto removeUser()");
        String[] idArray=ids.split(",");
        for(String id:idArray)
        {
            knService.removeUserById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/user/selectUser");
        return mv;
    }
    /*处理修改用户请求*/
    @RequestMapping(value = "user/updateUser")
    public ModelAndView updateUser(String flag,@ModelAttribute User user,ModelAndView mv)
    {System.out.println(" updateUser");
        if(flag.equals("1"))
        {//点击修改时的请求处理
            User target=knService.findUserById(user.getId());
            System.out.println("要更改的User对象："+target);
            mv.addObject("user",target);
            mv.setViewName("user/showUpdateUser");
        }else
        {//修改后的请求处理
            System.out.println("修改后的User对象："+user);
            knService.modifyUser(user);
            mv.setViewName("redirect:/user/selectUser");
        }
        return mv;
    }
    /*处理添加请求*/
    @RequestMapping(value = "/user/addUser")
    public ModelAndView addUser(//left.jsp发送过来的请求http://localhost:8080/user/addUser?flag=1
            String flag,
            @ModelAttribute User user,
            ModelAndView mv
    )
    {
        System.out.println(" addUser()");
        System.out.println(" /user/addUser flag="+flag);
        if (flag.equals("1"))
            mv.setViewName("user/showAddUser");
        else
        {
            knService.addUser(user);
            mv.setViewName("redirect:/user/selectUser");
        }
        return mv;
    }
}
