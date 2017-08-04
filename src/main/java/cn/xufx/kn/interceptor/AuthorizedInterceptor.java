package cn.xufx.kn.interceptor;
import cn.xufx.kn.domain.User;
import cn.xufx.kn.util.common.KnConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**判断用户权限的springmvc拦截器
 * Created by xufuxiu on 2017/7/18.
 */
public class AuthorizedInterceptor implements HandlerInterceptor
{
    /*定义不需要拦截的请求*/
    private static final String[] IGNORE_URI={"/loginForm","/login","/404.html"};

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception
    {
        /*preHandle返回true时执行，用于清理资源*/
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception
    {
        /*preHandle返回true时执行*/
    }
    /*flag为true之前会一直被调用，直到sevletPath成为IGNORE_URI中的一个*/
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {/*在controller之前被调用，若返回值为false，请求结束*/
        System.out.println(" AuthorizedInterceptor.preHandle()");
        boolean flag=false;//默认用户没有登录
        String servletPath=request.getServletPath();
        for(String s:IGNORE_URI)
        {//判断请求是否需要拦截
            if(servletPath.contains(s))
            {
                flag=true;
                break;
            }
        }
        if (!flag)//拦截请求
        {/*1.获取session中的用户*/
            User user=(User)request.getSession().getAttribute(KnConstants.USER_SESSION);
            if (user==null)/*2.判断用户是否已经登录*/
            {/*如果没有登录，跳转到登陆页面*/
                request.setAttribute("message","请先登录再访问网站！");
                /*Forwards a request from a servlet to another resource，将http://localhost:8080/main请求转发到http://localhost:8080/loginForm*/
                request.getRequestDispatcher(KnConstants.LOGIN).forward(request,response);
                return flag;
            }
            else
            {
                flag=true;
            }
        }
        return flag;
    }

}
