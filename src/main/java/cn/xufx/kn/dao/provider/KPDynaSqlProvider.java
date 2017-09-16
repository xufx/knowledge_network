package cn.xufx.kn.dao.provider;
import cn.xufx.kn.domain.KnowledgePoint;
import cn.xufx.kn.util.tag.PageModel;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static cn.xufx.kn.util.common.KnConstants.KPTABLE;
/**
 * Created by xufuxiu on 2017/8/1.
 */
public class KPDynaSqlProvider
{
    public String selectWithParam(Map<String,Object> params)
    {//分页动态查询
        String sql=new SQL()
        {
            {
                SELECT("*");
                FROM(KPTABLE);
                if (params.get("kp") != null)
                {
                    KnowledgePoint kp = (KnowledgePoint) params.get("kp");

                    if (kp.getName() != null && !kp.getName().equals(""))
                    {
                        WHERE("NAME LIKE CONCAT('%',#{kp.name},'%') ");
                    }
                    if (kp.getRemark()!=null&&!kp.getRemark().equals(""))
                    {
                        WHERE("remark LIKE CONCAT('%',#{kp.remark},'%') ");
                    }
                    if (kp.getContent() != null && !kp.getContent().equals(""))
                    {
                        WHERE("content LIKE CONCAT('%',#{kp.content},'%') ");
                    }

                    if (kp.getDoc() != null)
                    {
                        WHERE("doc=#{kp.doc}");
                    }
                    if (kp.getVideo() != null )
                    {
                        WHERE("video=#{kp.video}");
                    }
                    if (kp.getLabels()!=null)
                    {
                        WHERE("labels=#{kp.labels}");
                    }
                    if (kp.getReference() != null && !kp.getReference().equals(""))
                    {
                        WHERE("reference LIKE CONCAT('%',#{kp.reference},'%') ");
                    }
                    if (kp.getMemo() != null && !kp.getMemo().equals(""))
                    {
                        WHERE("memo LIKE CONCAT('%',#{kp.memo},'%') ");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel")!=null)
        {
            PageModel p=(PageModel)params.get("pageModel");
            System.out.println("params.get('pageModel'):"+p);
            System.out.println("pageModel.firstLimitParam="+p.getFirstLimitParam());
            System.out.println("pageModel.pageSize="+p.getPageSize());
            int i=p.getFirstLimitParam();
            if (i<0)
            {
                sql+=" limit 1,#{pageModel.pageSize}";
            }
            else
            {
                sql+=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
            }
        }
        return sql;
    }
    public  String insertKP(KnowledgePoint kp)
    {
        return new SQL()
        {
            {
                INSERT_INTO(KPTABLE);
                if (kp.getName() != null )
                {
                    VALUES("name","#{name}");//前一个参数表示数据中表的列，后一个字段表示po中的值
                }
                if (kp.getRemark() != null )
                {
                    VALUES("remark","#{remark}");
                }
                if (kp.getContent() != null )
                {
                    VALUES("content","#{content}");
                }
                if (kp.getLearnGoal() != null )
                {
                    VALUES("learn_goal","#{learnGoal}");
                }
                if (kp.getExamFrequency()!= null )
                {
                    VALUES("exam_frequency","#{examFrequency}");
                }
                if (kp.getDoc()!= null )
                {
                    VALUES("doc","#{doc}");
                }
                if (kp.getVideo()!= null )
                {
                    VALUES("video","#{video}");
                }
                if (kp.getReference()!=null)
                {
                    VALUES("reference","#{reference}");
                }
                if (kp.getMemo()!=null)
                {
                    VALUES("memo","#{memo}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }
    public String count(Map<String,Object> params)
    {//动态查询总数量
        return  new SQL()
        {
            {
                SELECT("COUNT(*)");
                FROM (KPTABLE);
                if (params.get("kp")!=null)
                {
                    KnowledgePoint kp = (KnowledgePoint) params.get("kp");
                    /*if条件有无都行，若没有则查询所有数据*/

                    if (kp.getName() != null && !kp.getName().equals(""))
                    {
                        WHERE("NAME LIKE CONCAT('%',#{kp.name},'%') ");
                    }
                    if (kp.getRemark() != null && !kp.getRemark().equals(""))
                    {
                        WHERE("remark LIKE CONCAT('%',#{kp.remark},'%') ");
                    }
                    if (kp.getContent() != null && !kp.getContent().equals(""))
                    {
                        WHERE("content LIKE CONCAT('%',#{kp.content},'%') ");
                    }
                    if (kp.getLearnGoal() != null && !kp.getLearnGoal().equals(""))
                    {
                        WHERE("learn_goal LIKE CONCAT('%',#{kp.learnGoal},'%') ");
                    }

                    if (kp.getDoc() != null )
                    {
                        WHERE("doc LIKE CONCAT('%',#{kp.doc},'%') ");
                    }
                    if (kp.getVideo() != null)
                    {
                        WHERE("video LIKE CONCAT('%',#{kp.video},'%') ");
                    }
                    if (kp.getReference() != null && !kp.getReference().equals(""))
                    {
                        WHERE("reference LIKE CONCAT('%',#{kp.reference},'%') ");
                    }
                    if (kp.getMemo() != null && !kp.getMemo().equals(""))
                    {
                        WHERE("memo LIKE CONCAT('%',#{kp.memo},'%') ");
                    }

                }
            }
        }.toString();
    }
    public String updateKP(KnowledgePoint kp)
    {
        return  new SQL()
        {
            {
                UPDATE(KPTABLE);
                if(kp.getName()!= null)
                {
                    SET(" name = #{name} ");
                }
                if(kp.getRemark()!= null)
                {
                    SET(" remark = #{remark} ");
                }
                if (kp.getContent() != null)
                {
                    SET("content=#{content}");
                }
                if (kp.getLearnGoal()!=null)
                {
                    SET("learn_goal=#{learnGoal}");
                }

                if(kp.getExamFrequency()!= null)
                {
                    SET(" exam_frequency = #{examFrequency} ");
                }
                if(kp.getDoc()!= null)
                {
                    SET(" doc= #{doc} ");
                }
                if(kp.getVideo()!= null)
                {
                    SET(" video= #{video} ");
                }
                if(kp.getReference()!= null)
                {
                    SET(" reference = #{reference} ");
                }
                if(kp.getMemo() != null)
                {
                    SET(" memo = #{memo} ");
                }
                WHERE(" id = #{id} ");
            }

        }.toString();
    }
}
