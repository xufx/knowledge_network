package cn.xufx.kn.service.impl;
import cn.xufx.kn.dao.*;
import cn.xufx.kn.domain.*;
import cn.xufx.kn.service.KNService;
import cn.xufx.kn.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.sql.PseudoColumnUsage;
import java.util.*;
/**
 * Created by xufuxiu on 2017/8/2.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("knService")/*声明这是KPService的实现类*/
public class KNServiceImpl implements KNService
{
    @Autowired
    private UserDao userDao;
    @Autowired
    private KPDao kpDao;
    @Autowired
    private LabelDao labelDao;

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
        System.out.println("KNService params.get(user):"+params.get(user));
        int recordCount=userDao.count(params);//总的记录数
        pageModel.setRecordCount(recordCount);
        if (recordCount>0)
            params.put("pageModel",pageModel);
        System.out.println("KNServiceImpl params user:"+params.get(user));
        System.out.println("KNServiceImpl params pageModel:"+params.get(pageModel));
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


    /*对知识点进行增删改查*/
    @Override
    public List<KnowledgePoint> findKnowledgePoint(KnowledgePoint kp, PageModel pageModel)
    {//分页查询知识点方法的实现
        Map<String,Object> params=new HashMap<>();
        params.put("kp",kp);
        int recordCount=kpDao.count(params);
        System.out.println("recordCount-->>"+recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount>0)
        {//如果记录条数》0，开始分页：查询第几页的数据
            params.put("pageModel",pageModel);
        }
        List<KnowledgePoint> kps=kpDao.selectByPage(params);
        return kps;
    }
    @Override
    public void removeKnowledgePointById(Integer id)
    {
        kpDao.deleteById(id);
    }
    @Override
    public KnowledgePoint findKnowledgePointById(Integer id)
    {
        return kpDao.selectById(id);
    }
    @Override
    public void addKnowledgePoint(KnowledgePoint kp)
    {
        kpDao.insertKP(kp);
    }
    @Override
    public void modifyKnowledgePoint(KnowledgePoint kp)
    {
        kpDao.update(kp);
    }


    @Override
    public List<Label> findLabel(Label label, PageModel pageModel)
    {
        Map<String,Object> params=new HashMap<>();
        params.put("label",label);
        int recordCount=labelDao.count(params);
        System.out.println("recordCount-->>"+recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount>0)
        {//如果记录条数》0，开始分页：查询第几页的数据
            params.put("pageModel",pageModel);
        }
        List<Label> labels=labelDao.selectByPage(params);
        return labels;
    }
    @Override
    public void removeLabelById(Integer id)
    {
        labelDao.deleteById(id);
    }
    @Override
    public Label findLabelById(Integer id)
    {
        return labelDao.selectById(id);
    }
    @Override
    public void addLabel(Label label)
    {
        labelDao.save(label);
    }
    @Override
    public void modifyLabel(Label label)
    {
        labelDao.update(label);
    }
}
