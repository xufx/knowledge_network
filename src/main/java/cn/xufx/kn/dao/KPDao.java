package cn.xufx.kn.dao;
import cn.xufx.kn.dao.provider.KPDynaSqlProvider;
import cn.xufx.kn.domain.KnowledgePoint;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static cn.xufx.kn.util.common.KnConstants.KPTABLE;
/**
 * Created by xufuxiu on 2017/8/1.
 */
public interface KPDao
{
    /*插入知识点*/
    @InsertProvider(type = KPDynaSqlProvider.class, method = "insertKP")
    void insertKP(KnowledgePoint knowledgePoint);

    /*分页查询所有知识点*/
    @SelectProvider(type = KPDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "remark",column = "remark"),
            @Result(property = "content",column = "content"),
            @Result(property = "memo",column = "memo"),
            @Result(property = "learnGoal",column = "learn_goal"),
            @Result(property = "examFrequency",column = "exam_frequency"),
            @Result(property = "reference",column = "reference"),
            @Result(property = "doc",column = "doc"),
            @Result(property = "video",column = "video")
            /*@Result(property = "labels",column = "id",many = @Many
                    (
                            select = "",
                            fetchType = FetchType.EAGER
                    ))*/
        })
    List<KnowledgePoint> selectByPage(Map<String, Object> params);

    /*删除知识点*/
    @Delete("delete from " + KPTABLE + " where id=#{id}")
    void deleteById(Integer id);

    /*查询某一个知识点*/
    @Select("select * from " + KPTABLE + " WHERE id=#{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "remark",column = "remark"),
            @Result(property = "content",column = "content"),
            @Result(property = "memo",column = "memo"),
            @Result(property = "learnGoal",column = "learn_goal"),
            @Result(property = "examFrequency",column = "exam_frequency"),
            @Result(property = "reference",column = "reference"),
            @Result(property = "doc",column = "doc"),
            @Result(property = "video",column = "video")

    })
    KnowledgePoint selectById(Integer id);

    /*修改知识点*/
    @SelectProvider(type = KPDynaSqlProvider.class,method ="updateKP" )
    void update(KnowledgePoint kp);

    /*根据参数查询总数*/
    @SelectProvider(type = KPDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);
}
