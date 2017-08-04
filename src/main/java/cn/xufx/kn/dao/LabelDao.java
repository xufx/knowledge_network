package cn.xufx.kn.dao;
import cn.xufx.kn.dao.provider.LabelDynaSqlProvider;
import cn.xufx.kn.domain.Label;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static cn.xufx.kn.util.common.KnConstants.LABELTABLE;
/**
 * Created by xufuxiu on 2017/7/18.
 */
public interface LabelDao
{
   @Select("select * from " + LABELTABLE + " WHERE id=#{id}")
    Label selectById(Integer id);

    @Delete("delete from " + LABELTABLE + " where id=#{id}")
    void deleteById(Integer id);

    @UpdateProvider(type = LabelDynaSqlProvider.class,method ="updateLabel" )
    void update(Label label);

    /*动态查询用户*/
    @SelectProvider(type = LabelDynaSqlProvider.class, method = "selectWithParam")
    List<Label> selectByPage(Map<String, Object> params);

    /*根据参数查询用户总数*/
    @SelectProvider(type = LabelDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @InsertProvider(type = LabelDynaSqlProvider.class,method="insertLabel")
    @Options(useGeneratedKeys=true,keyProperty = "id")
    void save(Label label);
}
