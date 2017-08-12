package cn.xufx.kn.service.impl;
import cn.xufx.kn.dao.KPDao;
import cn.xufx.kn.dao.KPRDao;
import cn.xufx.kn.domain.KnowledgePoint;
import cn.xufx.kn.service.KNService;
import cn.xufx.kn.service.KPRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * Created by xiao on 2017/2/26/0026.
 */
@Service("kprService")
public class KPRelativeServiceImpl implements KPRelativeService
{
    @Autowired
    private KPRDao kprDao;
    @Autowired
    private KPDao kpDao;
    @Autowired
    private KNService knService;
    @Autowired
    private KPRelativeService kprService;


    /**************************************************
     * 知识点之间的前后缀关系
     * 数据表: kp_pre_next
     **************************************************/

    /*查询知识点的前续知识点编号*/
    public List<Integer> selectPreIds(Integer id)
    {
        List<Integer> pre_ids=kprDao.selectPreIds(id);
        return  pre_ids;
    }

    @Override
    public List<KnowledgePoint> selectPreKPS(Integer id)
    {
        KnowledgePoint kp=knService.findKnowledgePointById(id);

        /*查询前续知识点的编号*/
        List<Integer> pre_ids=selectPreIds(id);

        //List<Integer> pre_ids=kprService.selectPreIds(id);

        List<KnowledgePoint>pre_kps=new ArrayList<>();
        if (pre_ids.size()==0)
        {
            System.out.println("没有找到 "+kp.getName()+" 的前续知识点");
        }else
        {
            for (Integer i:pre_ids)
            {
                KnowledgePoint k=knService.findKnowledgePointById(i);
                pre_kps.add(k);
            }
        }
        return pre_kps;
    }

    /*查询知识点的后续知识点编号*/
    public List<Integer> selectNextIds(Integer id)
    {
        List<Integer> next_ids=kprDao.selectNextIds(id);
        return  next_ids;
    }
    /*查询后续知识点*/
    @Override
    public List<KnowledgePoint> selectNextKPS(Integer id)
    {
        KnowledgePoint kp=knService.findKnowledgePointById(id);
        /*查询后续知识点的编号*/
        List<Integer> next_ids=selectNextIds(id);

        List<KnowledgePoint>next_kps=new ArrayList<>();

        if (next_ids.size()==0)
        {
            System.out.println("没有找到 "+kp.getName()+" 的后续知识点");
        }else
        {
            for (Integer i:next_ids)
            {
                KnowledgePoint k=knService.findKnowledgePointById(i);
                next_kps.add(k);
            }
        }
        return next_kps;
    }

    /*查询知识点的相关知识点编号*/
    public List<Integer> selectRelateIds(Integer id)
    {
        List<Integer> relate_ids=kprDao.selectRelateIds(id);
        return  relate_ids;
    }
    /*查询相关知识点*/
    @Override
    public List<KnowledgePoint> selectRelateKPS(Integer id)
    {
        KnowledgePoint kp=knService.findKnowledgePointById(id);
        List<Integer> relate_ids=selectRelateIds(id);

        List<KnowledgePoint>relate_kps=new ArrayList<>();
        if (relate_ids.size()==0)
        {
            System.out.println("没有找到 "+kp.getName()+" 的相关知识点");
        }else
        {
            for(Integer i:relate_ids)
            {
                KnowledgePoint k=knService.findKnowledgePointById(i);
                relate_kps.add(k);
            }
        }
        return relate_kps;
    }

    /*插入前后续知识点*/
    @Override
    public void insertPreAndNext(Map<Integer, Integer> params)
    {
        kprDao.insertPreNext(params);
    }
}
