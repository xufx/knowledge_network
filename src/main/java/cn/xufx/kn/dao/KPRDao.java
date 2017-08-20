package cn.xufx.kn.dao;
import cn.xufx.kn.dao.provider.KPRDynaSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static cn.xufx.kn.util.common.KnConstants.KP_PRE_NEXT_TABLE;
import static cn.xufx.kn.util.common.KnConstants.KP_RELATE_TABLE;
/**
 * Created by xufuxiu on 2017/8/1.
 */
public interface KPRDao
{

    /*查询前续知识点编号*/
    @Select("select prekp_id from "+KP_PRE_NEXT_TABLE+" where nextkp_id = #{id}")
    List<Integer> selectPreIds(Integer id);

    /*查询后续知识点编号*/
    @Select("select nextkp_id from "+KP_PRE_NEXT_TABLE+" where prekp_id = #{id}")
    List<Integer> selectNextIds(Integer id);

    /*查询相关知识点编号*/
    @Select("select relatekp_id from "+KP_RELATE_TABLE+" where kp_id = #{id}")
    List<Integer> selectRelateIds(Integer id);

    /*插入前后知识点*/
    @InsertProvider(type = KPRDynaSqlProvider.class,method="insertPreNext")
    void insertPreNext(Map<Integer,Integer> params);

    /*插入相关知识点*/
    @InsertProvider(type = KPRDynaSqlProvider.class,method="insertRelate")
    void insertRelated(Integer firstID , Integer secondID);

    /*删除前续知识点*/
    @DeleteProvider(type = KPRDynaSqlProvider.class,method="deletePreKPS")
    void deletePreKPS(Integer id, String ids);

    /*删除知识点-前续知识点对*/
    @DeleteProvider(type = KPRDynaSqlProvider.class,method="deletePreNextKP")
    void deletePreNextKP(Integer pre_id, Integer id);
    /*删除相关知识点*/
    @DeleteProvider(type = KPRDynaSqlProvider.class,method="deleteRelateKPS")
    void deleteRelateKPS(Integer id, Integer relate_id);

}
