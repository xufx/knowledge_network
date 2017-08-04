package cn.xufx.kn.dao;
import org.apache.ibatis.annotations.*;
import cn.xufx.kn.dao.provider.UserDynaSqlProvider;
import cn.xufx.kn.domain.User;

import java.util.List;
import java.util.Map;

import static cn.xufx.kn.util.common.KnConstants.USERTABLE;
/**
 * Created by xufuxiu on 2017/7/18.
 */
public interface UserDao
{
    @Select("select * from " + USERTABLE + " WHERE loginname=#{loginname}and password=#{password}")
    User selectByLoginnameAndPassword(@Param("loginname") String loginname, @Param("password") String password);

    @Select("select * from " + USERTABLE + " WHERE id=#{id}")
    User selectById(Integer id);

    @Delete("delete from " + USERTABLE + " where id=#{id}")
    void deleteById(Integer id);

    @UpdateProvider(type = UserDynaSqlProvider.class,method ="updateUser" )
    void update(User user);

    /*动态查询用户*/
    @SelectProvider(type = UserDynaSqlProvider.class, method = "selectWithParam")
    List<User> selectByPage(Map<String, Object> params);

    /*根据参数查询用户总数*/
    @SelectProvider(type = UserDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @InsertProvider(type = UserDynaSqlProvider.class,method="insertUser")
    @Options(useGeneratedKeys=true,keyProperty = "id")
    void save(User user);
}
