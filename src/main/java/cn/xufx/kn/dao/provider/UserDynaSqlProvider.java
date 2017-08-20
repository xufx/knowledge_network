package cn.xufx.kn.dao.provider;
import org.apache.ibatis.jdbc.SQL;
import cn.xufx.kn.domain.User;

import java.util.Map;

import static cn.xufx.kn.util.common.KnConstants.USERTABLE;
/**
 * Created by xufuxiu on 2017/7/18.
 */
public class UserDynaSqlProvider
{
    public String selectWithParam(Map<String,Object> params)
    {//分页动态查询
        String sql=new SQL()
        {
            {
                SELECT("*");
                FROM(USERTABLE);
                if (params.get("user") != null)
                {System.out.println("UserDynaSqlProvider。selectWithParam（）");

                    User user = (User) params.get("user");
                    System.out.println("user:"+user);
                    if (user.getUsername() != null && !user.getUsername().equals(""))
                    {
                        WHERE("username LIKE CONCAT('%',#{user.username},'%') ");
                    }
                    if (user.getStatus() != null && !user.getStatus().equals(""))
                    {
                        WHERE("status LIKE CONCAT('%',#{user.status},'%') ");
                    }
                }
            }
        }.toString();
        /*if (params.get("pageModel")!=null)
        {
            sql+=" limit#{pageModel.firstLimitParam},#{pageModel.pageSize}";
            System.out.println("KPServiceImpl page sql:"+sql);
        }*/
        return sql;
    }

    public String count(Map<String,Object> params)
    {//动态查询总数量
        return  new SQL()
        {
            {
                SELECT("COUNT(*)");
                FROM (USERTABLE);
                if (params.get("user")!=null)
                {System.out.println(" UserDynaSqlProvider.count()");
                    User user = (User) params.get("user");
                    if (user.getUsername() != null && !user.getUsername().equals(""))
                    {
                        WHERE("username LIKE CONCAT('%',#{user.username},'%') ");
                    }
                    if (user.getStatus() != null && !user.getStatus().equals(""))
                    {
                        WHERE("status LIKE CONCAT('%',#{user.status},'%') ");
                    }
                }
            }
        }.toString();
    }

    public String insertUser(User user)
    {//动态插入用户
        return  new SQL()
        {
            {
                INSERT_INTO(USERTABLE);
                if (user.getUsername() != null && !user.getUsername().equals(""))
                {
                    VALUES("username","#{username}");
                }
                if (user.getStatus() != null && !user.getStatus().equals(""))
                {
                    VALUES("status","#{status}");
                }
                if (user.getLoginname() != null && !user.getLoginname().equals(""))
                {
                    VALUES("loginname","#{loginname}");
                }
                if (user.getPassword() != null && !user.getPassword().equals(""))
                {
                    VALUES("password","#{password}");
                }
            }

        }.toString();
    }

    public String updateUser(User user)
    {
        return  new SQL()
        {
            {
                UPDATE(USERTABLE);
                if (user.getUsername() != null )
                {
                    SET("username=#{username}");
                }
                if (user.getStatus() != null )
                {
                    SET("status=#{status}");
                }
                if (user.getLoginname() != null)
                {
                    SET("loginname=#{loginname}");
                }
                if (user.getPassword() != null)
                {
                    SET("password=#{password}");
                }
                WHERE("id=#{id}");
            }

        }.toString();
    }

}
