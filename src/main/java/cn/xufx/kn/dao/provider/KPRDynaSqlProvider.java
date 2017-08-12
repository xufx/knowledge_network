package cn.xufx.kn.dao.provider;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
import java.util.Set;

import static cn.xufx.kn.util.common.KnConstants.KP_PRE_NEXT_TABLE;
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
                        INSERT_INTO(KP_PRE_NEXT_TABLE);
                        Integer pre_id=entry.getKey();
                        Integer next_id=entry.getValue();
                        VALUES("prekp_id,nextkp_id","#{pre_id},#{next_id}");
                    }
                }
            }
        }.toString();
    }
}
