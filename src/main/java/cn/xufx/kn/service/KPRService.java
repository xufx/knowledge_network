package cn.xufx.kn.service;
import cn.xufx.kn.domain.KnowledgePoint;

import java.util.List;
import java.util.Map;
/**
 * Created by xufuxiu on 2017/8/5.
 */
public interface KPRService
{
    /**************************************************
     * 知识点之间的前后缀关系
     * 数据表: kp_affix
     **************************************************/

    /*查询前续知识点信息*/
    List<KnowledgePoint> selectPreKPS(Integer id);
    /*查询后续知识点信息*/
    List<KnowledgePoint> selectNextKPS(Integer id);
    /*查询相关知识点信息*/
    List<KnowledgePoint> selectRelateKPS(Integer id);

    /*查询一个知识点的所有关联知识点*/
    List<List<KnowledgePoint>> selectAllKPR(Integer id);

    /*插入前后续知识点*/
    void insertPreAndNext(Map<Integer,Integer> params);

    /*插入相关知识点*/
    void insertRelate(Integer firstID,Integer secondID);

    /*判断是否有后续知识点*/
    Boolean hasNext(Integer preID,Integer nextID);

    /*判断是否有前续知识点*/
    Boolean hasPre(Integer preID,Integer nextID);

    /*判断是否有相关知识点*/
    Boolean hasRelate(Integer ID,Integer relateID);

    /*判断是否有关系，List有三个值，第一个表示后续关系，第二个表示前续关系，第三个表示相关关系*/
    List<Boolean> haveRelations(Integer firstID, Integer relation,Integer secondID);

    /*添加关系*/
    void addRelation(Integer firstID, Integer relation,Integer secondID);

    /*删除前续知识点集合*/
    void deletePreKPS(String ids,Integer id);
    /*删除后续知识点集合*/
    void deleteNextKPS(Integer id,String ids);

    /*删除相关知识点*/
    void deleteRelateKPS(Integer kp_id,String ids);


}
