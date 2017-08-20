package cn.xufx.kn.service;
import cn.xufx.kn.domain.Label;
import cn.xufx.kn.util.tag.PageModel;

import java.util.List;
/**
 * Created by xufuxiu on 2017/8/2.
 */
public interface LabelService
{

    /*标签管理*/
    List<Label> findLabel(Label kp, PageModel pageModel);
    void removeLabelById(Integer id);
    Label findLabelById(Integer id);
    void addLabel(Label kp);
    void modifyLabel(Label kp);

}
