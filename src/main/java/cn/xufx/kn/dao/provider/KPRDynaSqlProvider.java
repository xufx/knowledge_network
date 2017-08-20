package cn.xufx.kn.dao.provider;
import org.apache.ibatis.jdbc.SQL;

import java.util.*;

import static cn.xufx.kn.util.common.KnConstants.KP_PRE_NEXT_TABLE;
import static cn.xufx.kn.util.common.KnConstants.KP_RELATE_TABLE;
/**
 * Created by xufuxiu on 2017/8/9.
 */
public class KPRDynaSqlProvider
{
    public String insertPreNext(Map<Integer,Integer>params)
    {
        return new SQL()
        {
            {
                if (params.size()!=0)
                {
                    Set<Map.Entry<Integer,Integer>>sets=params.entrySet();
                    for(Map.Entry<Integer,Integer> entry:sets)
                    {
                        Integer pre_id=entry.getKey();
                        Integer next_id=entry.getValue();
                        System.out.println(pre_id);
                        System.out.println(next_id);
                        INSERT_INTO(KP_PRE_NEXT_TABLE);
                        if (pre_id!=null && next_id!=null)
                        {
                            VALUES("prekp_id",pre_id.toString());
                        }
                        if(pre_id!=null && next_id!=null)
                        {
                            VALUES("nextkp_id",next_id.toString());
                        }
                    }
                }
            }
        }.toString();
    }

    public String insertRelate(Integer firstID, Integer secondID)
    {
        return  new SQL()
        {
            {
                INSERT_INTO(KP_RELATE_TABLE);
                if (firstID!=null && secondID!=null)
                {
                    VALUES("kp_id",firstID.toString());
                }
                if (firstID!=null && secondID!=null)
                {
                    VALUES("relatekp_id",secondID.toString());
                }
            }
        }.toString();
    }

    public String deletePreKPS(Integer id, String ids)
    {
        return new SQL()
        {
            {
                String[] str=ids.split(",");
                for (String s:str)
                {
                    Integer i=Integer.parseInt(s);
                    DELETE_FROM(KP_PRE_NEXT_TABLE);
                    WHERE(" prekp_id="+id+" and nextkp_id="+i);
                }
            }
        }.toString();
    }

    public String deletePreNextKP(Integer pre_id,Integer id)
    {
        return new SQL()
        {
            {
                DELETE_FROM(KP_PRE_NEXT_TABLE);
                WHERE("prekp_id="+pre_id);
                WHERE("nextkp_id="+id);
            }
        }.toString();
    }

    public String deleteRelateKPS(Integer id, Integer relate_id)
    {
        return new SQL()
        {
            {
                DELETE_FROM(KP_RELATE_TABLE);
                WHERE("kp_id="+id+" and relatekp_id="+relate_id);
            }
        }.toString();
    }
}
