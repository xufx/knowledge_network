package cn.xufx.kn.domain;
import java.io.Serializable;
/**
 * Created by xufuxiu on 2017/8/1.
 */
public class Label  implements Serializable
{//知识点所属的标签，一个知识点对应多个标签
    private Integer id;
    private String name;
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    @Override
    public String toString()
    {
        return this.getClass()+"[id="+id+",name="+name+"]";
    }
}
