package cn.xufx.kn.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by xufuxiu on 2017/7/19.
 */
@Controller
public class FormController
{
    @RequestMapping(value = "/{formName}")
    public String loginForm(@PathVariable String formName)
    {
        System.out.println(" FormController");
        System.out.println("formName:"+formName);
        return formName;//请求变为http://localhost:8080/WEB-INF/jsp/loginform.jsp springmvc-config.xml配置了视图匹配路径
    }
}
