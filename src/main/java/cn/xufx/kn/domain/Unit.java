package cn.xufx.kn.domain;
/**
 * Created by xufuxiu on 2017/8/1.
 */
public class Unit
{
    private Integer id;
    private String name;
    private String remark;//简介
    private String symbol;//单位符号
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
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getSymbol()
    {
        return symbol;
    }
    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }
    @Override
    public String toString()
    {
        return this.getClass()+"[id="+id+",name="+name+",remark="+remark+",symbol="+symbol+"]";
    }
}
