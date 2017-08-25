package cn.xufx.kn.service.impl;
import cn.xufx.kn.dao.*;
import cn.xufx.kn.domain.*;
import cn.xufx.kn.service.KPService;
import cn.xufx.kn.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;
/**
 * Created by xufuxiu on 2017/8/2.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("kpService")/*声明这是kpService的实现类*/
public class KPServiceImpl implements KPService
{
    @Autowired
    private KPDao kpDao;
    @Autowired
    private LabelDao labelDao;

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
    public List<KnowledgePoint> findAllKP()
    {
        return kpDao.findAll();
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
    /*根据知识点的名称查询知识点*/
    @Override
    public KnowledgePoint selectKPByName(String name)
    {
        KnowledgePoint kp=kpDao.selectByName(name);
        return kp;
    }

}
