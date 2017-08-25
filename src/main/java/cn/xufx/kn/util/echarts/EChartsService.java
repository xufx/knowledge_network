package cn.xufx.kn.util.echarts;
import cn.xufx.kn.domain.KnowledgePoint;
import cn.xufx.kn.service.KPRService;
import cn.xufx.kn.service.KPService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
/**
 */
@Service("echartsService")
public class EChartsService {

    @Resource
    private KPService kpService;

    @Resource
    private KPRService kprService;

    /*spread：是否允许中心知识点的关联知识点显示它们的关联知识点*/
    private boolean spread = false;

    public EChartsProduct build(Integer kp_id, Integer level, boolean spread)
    {
        /*根据id从knowledge_point表中查询出对象*/
        KnowledgePoint kp = kpService.findKnowledgePointById(kp_id);
        if (kp == null) {
            return null;
        }
        EChartsBuilder builder = new EChartsBuilder(kp.getName());

        this.spread = spread;

        Node node = new Node(kp.getName(), Node.CENTER_TYPE);
        node.setValue(kp);
        builder.add(node);//将节点添加到节点集之中

        /*查询知识点的前后续知识点*/
        searchPreAndNext(builder, kp_id, level);
        /*查询知识点的父子知识点*/
        //searchSubordination(builder, kp_id);
        searchRelate(builder, kp_id);
        /*查询完知识点的关系后，将边加入边集之中*/
        return builder.build();//返回EchartsProduct对象，重新编排节点和边
    }
    /*查询知识点的前后续知识点
    * kp_id：传过来的知识点编号
    * level：拓展的层数*/
    private void searchPreAndNext(EChartsBuilder builder, Integer kp_id, Integer level)
    {
        Edge edge = null;
        /*从kp_affix表中查询知识点的前续知识点编号集合*/
        List<Integer> prefix_ids = kprService.selectPreIds(kp_id);
        for (Integer id : prefix_ids)
        {/*根据查询到的知识点id，再查询知识点的前续知识点。level随着拓展深度而递减，若中心知识点N1拓展3层，则N1的前续知识点N4拓展2层，N4的前续知识点N5拓展1层,查找N1的前续知识点N4、N5，查找N4的前续知识点、N5的前续知识点N8*/
        System.out.println(" prefix_ids:"+id);
            searchPre(builder, id, level - 1, Node.PREFIX_TYPE);//继续查询前续知识点
            edge = new Edge(Edge.AFFIX_TYPE, id, kp_id);//（type,source,target）
            builder.add(edge);//添加边
        }
        /*查询知识点的后续知识点*/
        List<Integer> postfix_ids = kprService.selectNextIds(kp_id);
        for (Integer id : postfix_ids)
        {System.out.println(" postfix_ids:"+id);
            searchNext(builder, id, level - 1, Node.POSTFIX_TYPE);//继续查询后续知识点
            edge = new Edge(Edge.AFFIX_TYPE, kp_id, id);//（type,source,target）
            builder.add(edge);//添加边
        }
    }
    /*查询kp_id的前续知识点*/
    private void searchPre(EChartsBuilder builder, int kp_id, int level, String type)
    {
        /*判断节点编号集合中是否已经包含知识点kp_id*/
        if (builder.contains(kp_id)) return;

        /*根据id查询一个知识点的信息*/
        KnowledgePoint kp = kpService.findKnowledgePointById(kp_id);
        Node node = new Node(kp.getName(), type);
        node.setValue(kp);
        builder.add(node);

        if (level <= 0)
        {
            return;
        } else
        {
            List<Integer> prefix_ids = kprService.selectPreIds(kp_id);
            for (Integer id : prefix_ids)
            {
                searchPre(builder, id, level - 1, type);
                /*边的类型，源、目标*/
                Edge edge = new Edge(Edge.AFFIX_TYPE, id, kp_id);
                builder.add(edge);
            }

            if (this.spread)
            {
                List<Integer> postfix_ids = kprService.selectNextIds(kp_id);
                for (Integer id : postfix_ids) {
                    searchNext(builder, id, level - 1, Node.POSTFIX_TYPE);
                    Edge edge = new Edge(Edge.AFFIX_TYPE, kp_id, id);
                    builder.add(edge);
                }
            }
        }
    }

    private void searchNext(EChartsBuilder builder, Integer kp_id, int level, String type) {
       if (builder.contains(kp_id)) return;

        /*根据id查询知识点的记录*/
        KnowledgePoint kp = kpService.findKnowledgePointById(kp_id);
        Node node = new Node(kp.getName(), type);
        node.setValue(kp);
        builder.add(node);

        if (level <= 0) {
            return;
        } else
        {/*查询后续知识点*/
            List<Integer> postfix_ids = kprService.selectNextIds(kp_id);
            for (Integer id : postfix_ids)
            {
                /*将id节点添加到Node中*/
                searchNext(builder, id, level - 1, type);
                Edge edge = new Edge(Edge.AFFIX_TYPE, kp_id, id);
                builder.add(edge);
            }

            if (this.spread)
            {
                List<Integer> prefix_ids = kprService.selectPreIds(kp_id);
                for (Integer id : prefix_ids) {
                    searchPre(builder, id, level - 1, Node.PREFIX_TYPE);
                    Edge edge = new Edge(Edge.AFFIX_TYPE, id, kp_id);
                    builder.add(edge);
                }
            }
        }
    }
    private void searchRelate(EChartsBuilder builder, Integer kp_id)
    {
        Node node = null;
        Edge edge = null;
        List<Integer> relate_ids=kprService.selectRelateIds(kp_id);
        for(Integer relate_id:relate_ids)
        {
            edge = new Edge(Edge.RELATE_TYPE, relate_id, kp_id);
            builder.add(edge);
        }

    }

}
