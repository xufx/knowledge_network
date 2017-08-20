package cn.xufx.kn.service.impl;
import cn.xufx.kn.dao.LabelDao;
import cn.xufx.kn.domain.Label;
import cn.xufx.kn.service.LabelService;
import cn.xufx.kn.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;
/**
 * Created by xufuxiu on 2017/8/2.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("labelService")/*声明这是kpService的实现类*/
public class LabelServiceImpl implements LabelService
{
    @Autowired
    private LabelDao labelDao;


    @Override
    public List<Label> findLabel(Label label, PageModel pageModel)
    {
        Map<String,Object> params=new HashMap<>();
        params.put("label",label);
        int recordCount=labelDao.count(params);
        System.out.println("recordCount-->>"+recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount>0)
        {//如果记录条数》0，开始分页：查询第几页的数据
            params.put("pageModel",pageModel);
        }
        List<Label> labels=labelDao.selectByPage(params);
        return labels;
    }
    @Override
    public void removeLabelById(Integer id)
    {
        labelDao.deleteById(id);
    }
    @Override
    public Label findLabelById(Integer id)
    {
        return labelDao.selectById(id);
    }
    @Override
    public void addLabel(Label label)
    {
        labelDao.save(label);
    }
    @Override
    public void modifyLabel(Label label)
    {
        labelDao.update(label);
    }

}
