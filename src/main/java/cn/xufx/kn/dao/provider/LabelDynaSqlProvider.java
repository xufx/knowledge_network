package cn.xufx.kn.dao.provider;
import cn.xufx.kn.domain.Label;
import cn.xufx.kn.util.tag.PageModel;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static cn.xufx.kn.util.common.KnConstants.LABELTABLE;
/**
 * Created by xufuxiu on 2017/8/4.
 */
public class LabelDynaSqlProvider
{
    public String selectWithParam(Map<String,Object> params)
    {//分页动态查询
        String sql=new SQL()
        {
            {
                SELECT("*");
                FROM(LABELTABLE);
                if (params.get("label") != null)
                {
                    Label label = (Label) params.get("label");

                    if (label.getName() != null && !label.getName().equals(""))
                    {
                        WHERE("NAME LIKE CONCAT('%',#{label.name},'%') ");
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
                sql+=" limit 0,#{pageModel.pageSize}";
            }
            else
            {
                sql+=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
            }
        }
        return sql;
    }
    public  String insertLabel(Label label)
    {
        return new SQL()
        {
            {
                INSERT_INTO(LABELTABLE);
                if (label.getName() != null )
                {
                    VALUES("name","#{name}");//前一个参数表示数据中表的列，后一个字段表示po中的值
                }

            }
        }.toString();
    }
    public String count(Map<String,Object> params)
    {//动态查询总数量
        return  new SQL()
        {
            {
                SELECT("COUNT(*)");
                FROM (LABELTABLE);
                if (params.get("label")!=null)
                {
                    Label label = (Label) params.get("label");
                    /*if条件有无都行，若没有则查询所有数据*/

                    if (label.getName() != null && !label.getName().equals(""))
                    {
                        WHERE("NAME LIKE CONCAT('%',#{label.name},'%') ");
                    }

                }
            }
        }.toString();
    }
    public String updateLabel(Label label)
    {
        return  new SQL()
        {
            {
                UPDATE(LABELTABLE);
                if(label.getName()!= null)
                {
                    SET(" name = #{name} ");
                }
                WHERE(" id = #{id} ");
            }

        }.toString();
    }
}
