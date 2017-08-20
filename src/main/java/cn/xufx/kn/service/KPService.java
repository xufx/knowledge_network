package cn.xufx.kn.service;
import cn.xufx.kn.domain.*;
import cn.xufx.kn.util.tag.PageModel;

import java.util.List;
/**
 * Created by xufuxiu on 2017/8/2.
 */
public interface KPService
{
    /*对知识点进行操作*/
    /*分页查询所有知识点*/
    List<KnowledgePoint> findKnowledgePoint(KnowledgePoint kp, PageModel pageModel);

    /*直接查询所有知识点，不分页*/
    List<KnowledgePoint> findAllKP();

    void removeKnowledgePointById(Integer id);/*删除知识点*/
    KnowledgePoint findKnowledgePointById(Integer id);/*查询某个知识点*/
    void addKnowledgePoint(KnowledgePoint kp);/*插入知识点*/
    void modifyKnowledgePoint(KnowledgePoint kp);/*修改知识点*/
    KnowledgePoint selectKPByName(String name);/*根据知识点的名称查询知识点*/

    /*知识点的前续知识点增删改查*/
    List<KnowledgePoint>findPreKP(KnowledgePoint kp,PageModel pageModel);
    List<KnowledgePoint>findPreKP(List<Integer>ids,PageModel pageModel);
    /*移除一个知识点的所有前续知识点*/
    void  removePreKPById(List<Integer> ids);

}
