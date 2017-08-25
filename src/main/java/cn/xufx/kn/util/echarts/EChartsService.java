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

    /*spread���Ƿ���������֪ʶ��Ĺ���֪ʶ����ʾ���ǵĹ���֪ʶ��*/
    private boolean spread = false;

    public EChartsProduct build(Integer kp_id, Integer level, boolean spread)
    {
        /*����id��knowledge_point���в�ѯ������*/
        KnowledgePoint kp = kpService.findKnowledgePointById(kp_id);
        if (kp == null) {
            return null;
        }
        EChartsBuilder builder = new EChartsBuilder(kp.getName());

        this.spread = spread;

        Node node = new Node(kp.getName(), Node.CENTER_TYPE);
        node.setValue(kp);
        builder.add(node);//���ڵ���ӵ��ڵ㼯֮��

        /*��ѯ֪ʶ���ǰ����֪ʶ��*/
        searchPreAndNext(builder, kp_id, level);
        /*��ѯ֪ʶ��ĸ���֪ʶ��*/
        //searchSubordination(builder, kp_id);
        searchRelate(builder, kp_id);
        /*��ѯ��֪ʶ��Ĺ�ϵ�󣬽��߼���߼�֮��*/
        return builder.build();//����EchartsProduct�������±��Žڵ�ͱ�
    }
    /*��ѯ֪ʶ���ǰ����֪ʶ��
    * kp_id����������֪ʶ����
    * level����չ�Ĳ���*/
    private void searchPreAndNext(EChartsBuilder builder, Integer kp_id, Integer level)
    {
        Edge edge = null;
        /*��kp_affix���в�ѯ֪ʶ���ǰ��֪ʶ���ż���*/
        List<Integer> prefix_ids = kprService.selectPreIds(kp_id);
        for (Integer id : prefix_ids)
        {/*���ݲ�ѯ����֪ʶ��id���ٲ�ѯ֪ʶ���ǰ��֪ʶ�㡣level������չ��ȶ��ݼ���������֪ʶ��N1��չ3�㣬��N1��ǰ��֪ʶ��N4��չ2�㣬N4��ǰ��֪ʶ��N5��չ1��,����N1��ǰ��֪ʶ��N4��N5������N4��ǰ��֪ʶ�㡢N5��ǰ��֪ʶ��N8*/
        System.out.println(" prefix_ids:"+id);
            searchPre(builder, id, level - 1, Node.PREFIX_TYPE);//������ѯǰ��֪ʶ��
            edge = new Edge(Edge.AFFIX_TYPE, id, kp_id);//��type,source,target��
            builder.add(edge);//��ӱ�
        }
        /*��ѯ֪ʶ��ĺ���֪ʶ��*/
        List<Integer> postfix_ids = kprService.selectNextIds(kp_id);
        for (Integer id : postfix_ids)
        {System.out.println(" postfix_ids:"+id);
            searchNext(builder, id, level - 1, Node.POSTFIX_TYPE);//������ѯ����֪ʶ��
            edge = new Edge(Edge.AFFIX_TYPE, kp_id, id);//��type,source,target��
            builder.add(edge);//��ӱ�
        }
    }
    /*��ѯkp_id��ǰ��֪ʶ��*/
    private void searchPre(EChartsBuilder builder, int kp_id, int level, String type)
    {
        /*�жϽڵ��ż������Ƿ��Ѿ�����֪ʶ��kp_id*/
        if (builder.contains(kp_id)) return;

        /*����id��ѯһ��֪ʶ�����Ϣ*/
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
                /*�ߵ����ͣ�Դ��Ŀ��*/
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

        /*����id��ѯ֪ʶ��ļ�¼*/
        KnowledgePoint kp = kpService.findKnowledgePointById(kp_id);
        Node node = new Node(kp.getName(), type);
        node.setValue(kp);
        builder.add(node);

        if (level <= 0) {
            return;
        } else
        {/*��ѯ����֪ʶ��*/
            List<Integer> postfix_ids = kprService.selectNextIds(kp_id);
            for (Integer id : postfix_ids)
            {
                /*��id�ڵ���ӵ�Node��*/
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
