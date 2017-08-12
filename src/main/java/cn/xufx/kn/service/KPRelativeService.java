package cn.xufx.kn.service;
import cn.xufx.kn.domain.KnowledgePoint;

import java.util.List;
import java.util.Map;
/**
 * Created by xufuxiu on 2017/8/5.
 */
public interface KPRelativeService
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
    public List<KnowledgePoint> selectRelateKPS(Integer id);

    void insertPreAndNext(Map<Integer,Integer> params);


}
