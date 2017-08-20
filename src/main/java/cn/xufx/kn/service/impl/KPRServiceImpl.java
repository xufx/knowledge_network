package cn.xufx.kn.service.impl;
import cn.xufx.kn.dao.KPDao;
import cn.xufx.kn.dao.KPRDao;
import cn.xufx.kn.domain.KnowledgePoint;
import cn.xufx.kn.service.KPService;
import cn.xufx.kn.service.KPRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * Created by xiao on 2017/2/26/0026.
 */
@Service("kprService")
public class KPRServiceImpl implements KPRService
{
    @Autowired
    private KPRDao kprDao;
    @Autowired
    private KPDao kpDao;
    @Autowired
    private KPService KPService;
    @Autowired
    private KPRService kprService;


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
        KnowledgePoint kp= KPService.findKnowledgePointById(id);

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
                KnowledgePoint k= KPService.findKnowledgePointById(i);
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
        KnowledgePoint kp= KPService.findKnowledgePointById(id);
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
                KnowledgePoint k= KPService.findKnowledgePointById(i);
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
        KnowledgePoint kp= KPService.findKnowledgePointById(id);
        List<Integer> relate_ids=selectRelateIds(id);

        List<KnowledgePoint>relate_kps=new ArrayList<>();
        if (relate_ids.size()==0)
        {
            System.out.println("没有找到 "+kp.getName()+" 的相关知识点");
        }else
        {
            for(Integer i:relate_ids)
            {
                KnowledgePoint k= KPService.findKnowledgePointById(i);
                relate_kps.add(k);
            }
        }
        return relate_kps;
    }
    /*查询一个知识点的所有关联知识点*/
    @Override
    public List<List<KnowledgePoint>> selectAllKPR(Integer id)
    {
        List<List<KnowledgePoint>> allRelateKPS=new ArrayList<>();
            List<KnowledgePoint> pre_kps=kprService.selectPreKPS(id);
            List<KnowledgePoint> next_kps=kprService.selectNextKPS(id);
            List<KnowledgePoint> relate_kps=kprService.selectRelateKPS(id);
            allRelateKPS.add(pre_kps);
            allRelateKPS.add(next_kps);
            allRelateKPS.add(relate_kps);
        return allRelateKPS;
    }
    /*插入前后续知识点*/
    @Override
    public void insertPreAndNext(Map<Integer, Integer> params)
    {
        kprDao.insertPreNext(params);
    }
    @Override
    public void insertRelate(Integer firstID, Integer secondID)
    {
        kprDao.insertRelated(firstID,secondID);
    }
    /*查询后续知识点是否存在*/
    @Override
    public Boolean hasNext(Integer preID, Integer nextID)
    {
        List<Integer>next_ids=kprDao.selectNextIds(preID);
        if(next_ids.contains(nextID))return true;
        else return false;
    }
    @Override
    public Boolean hasPre(Integer preID, Integer nextID)
    {
        List<Integer>pre_ids=kprDao.selectPreIds(nextID);
        if(pre_ids.contains(preID))return true;
        else return false;
    }
    @Override
    public Boolean hasRelate(Integer ID, Integer relateID)
    {
        List<Integer>relate_ids=kprDao.selectRelateIds(ID);
        if(relate_ids.contains(relateID))return true;
        else return false;
    }
    @Override
    public List<Boolean> haveRelations(Integer firstID, Integer relation, Integer secondID)
    {
        Boolean hasNext=false;
        Boolean hasPre=false;
        Boolean hasRelate=false;
        Boolean exist=false;
        List<Boolean> list=new ArrayList<>();
        /*判断关系类型1,2,3分别表示后续、前续、相关*/
        if(relation==1)
        {
            hasNext=kprService.hasNext(firstID,secondID);
           /* exist=kprService.searchError(firstID, secondID);*/
        }else if (relation==2)
        {
            hasPre=kprService.hasPre(firstID,secondID);
            /*exist=kprService.searchError(firstID, secondID);*/
        }else
        {
            hasRelate=kprService.hasRelate(firstID,secondID);
        }

        list.add(hasNext);
        list.add(hasPre);
        list.add(hasRelate);
        list.add(exist);
        return list;
    }
    @Override
    public void addRelation(Integer firstID, Integer relation, Integer secondID)
    {
        if(relation==1)
        {
            Map<Integer,Integer>map=new HashMap<>();
            map.put(firstID,secondID);
            kprService.insertPreAndNext(map);
        }else  if (relation==2)
        {
            Map<Integer,Integer>map=new HashMap<>();
            map.put(secondID,firstID);
            kprService.insertPreAndNext(map);
        }
        else if(relation==3)
        {
            kprService.insertRelate(firstID,secondID);
        }
    }
    /*@Override
    public Boolean searchError(Integer firstID, Integer secondID)
    {
        List<Integer> pre_ids1=kprDao.selectPreIds(secondID);
        List<Integer> next_ids1=kprDao.selectNextIds(firstID);
        if(pre_ids1.contains(secondID)&& next_ids1.contains(firstID))
        {
            System.out.println("firstID:"+firstID);
            System.out.println("secondID:"+secondID);
            return true;
        }else return false;

    }*/

    /*删除前续或后续知识点id:知识点，ids：前续或后续知识点集合*/
    @Override
    public void deletePreKPS(String ids,Integer id)
    {
        if(id!=null&&ids!=null)
        {
            String[] idArray=ids.split(",");
            for (String pre_id:idArray)
            {
                kprDao.deletePreNextKP(Integer.parseInt(pre_id),id);
            }
        }else
        {
            System.out.println("知识点-前续知识点对不存在");
        }

    }
    @Override
    public void deleteNextKPS(Integer id, String ids)
    {
        if(id!=null&&ids!=null)
        {
            String[] idArray=ids.split(",");
            for (String next_id:idArray)
            {
                kprDao.deletePreNextKP(id, Integer.parseInt(next_id));
            }
        }else
        {
            System.out.println("知识点-后续知识点对不存在");
        }
    }

    /*@Override
    public void deletePreKP(Integer pre_id, Integer next_id)
    {
        kprDao.deletePreNextKP(pre_id,next_id);
    }*/
    @Override
    public void deleteRelateKPS(Integer kp_id, String ids)
    {
        if(kp_id!=null&&ids!=null)
        {
            String[] idArray=ids.split(",");
            for (String relate_id:idArray)
            {
                kprDao.deleteRelateKPS(kp_id, Integer.parseInt(relate_id));
            }
        }else
        {
            System.out.println("知识点-相关知识点对不存在");
        }
    }
}
