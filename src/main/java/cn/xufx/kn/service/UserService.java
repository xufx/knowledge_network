package cn.xufx.kn.service;
import cn.xufx.kn.domain.*;
import cn.xufx.kn.util.tag.PageModel;

import java.util.List;
/**
 * Created by xufuxiu on 2017/8/2.
 */
public interface UserService
{
    /*对用户进行增删改查*/
    User login(String loginname, String password);
    User findUserById(Integer id);
    List<User> findUser(User user, PageModel pageModel);
    void removeUserById(Integer id);
    void modifyUser(User user);
    void addUser(User user);

}
