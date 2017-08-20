package cn.xufx.kn.service.impl;
import cn.xufx.kn.dao.UserDao;
import cn.xufx.kn.domain.User;
import cn.xufx.kn.service.UserService;
import cn.xufx.kn.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;
/**
 * Created by xufuxiu on 2017/8/2.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("userService")/*声明这是kpService的实现类*/
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String loginname, String password)
    {System.out.println(" KNServiceImpl的login方法实现");
        return userDao.selectByLoginnameAndPassword(loginname,password);
    }
    @Override
    public User findUserById(Integer id)
    {
        return userDao.selectById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel pageModel)
    {//分页查询所有用户
        System.out.println(" HrmServieImpl findUser()");
        Map<String,Object>params=new HashMap<>();
        params.put("user",user);
        System.out.println("KPService params.get(user):"+params.get(user));
        int recordCount=userDao.count(params);//总的记录数
        pageModel.setRecordCount(recordCount);
        if (recordCount>0)
            params.put("pageModel",pageModel);
        System.out.println("KPServiceImpl params user:"+params.get(user));
        System.out.println("KPServiceImpl params pageModel:"+params.get(pageModel));
        List<User> users=userDao.selectByPage(params);
        return users;
    }
    @Transactional(readOnly = true)
    @Override
    public void removeUserById(Integer id)
    {
        userDao.deleteById(id);
    }
    @Override
    public void modifyUser(User user)
    {
        userDao.update(user);
    }
    @Override
    public void addUser(User user)
    {
        userDao.save(user);
    }

}
