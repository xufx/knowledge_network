package cn.xufx.kn.service;
import cn.xufx.kn.domain.*;
import cn.xufx.kn.util.tag.PageModel;

import java.util.List;
/**
 * Created by xufuxiu on 2017/8/2.
 */
public interface KNService
{
    /*对用户进行增删改查*/
    User login(String loginname, String password);
    User findUserById(Integer id);
    List<User> findUser(User user,PageModel pageModel);
    void removeUserById(Integer id);
    void modifyUser(User user);
    void addUser(User user);

    /*对知识点进行操作*/
    /*查询所有知识点*/
    List<KnowledgePoint> findKnowledgePoint(KnowledgePoint kp, PageModel pageModel);

    void removeKnowledgePointById(Integer id);/*删除知识点*/
    KnowledgePoint findKnowledgePointById(Integer id);/*查询某个知识点*/
    void addKnowledgePoint(KnowledgePoint kp);/*插入知识点*/
    void modifyKnowledgePoint(KnowledgePoint kp);/*修改知识点*/

    /*标签管理*/
    List<Label> findLabel(Label kp, PageModel pageModel);
    void removeLabelById(Integer id);
    Label findLabelById(Integer id);
    void addLabel(Label kp);
    void modifyLabel(Label kp);
}
