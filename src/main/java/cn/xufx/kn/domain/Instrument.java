package cn.xufx.kn.domain;
/**
 * Created by xufuxiu on 2017/8/1.
 */
public class Instrument
{//仪器
    private Integer id;
    private String name;
    private String remark;//简介
    private String instruction;//使用方法
    private String memo;//备注
    //private String image;
    private String reference;
    private String doc;//学习文档
    private String video;//学习视频
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
    public String getInstruction()
    {
        return instruction;
    }
    public void setInstruction(String instruction)
    {
        this.instruction = instruction;
    }
    public String getMemo()
    {
        return memo;
    }
    public void setMemo(String memo)
    {
        this.memo = memo;
    }
    public String getReference()
    {
        return reference;
    }
    public void setReference(String reference)
    {
        this.reference = reference;
    }
    public String getDoc()
    {
        return doc;
    }
    public void setLearnDoc(String doc)
    {
        this.doc = doc;
    }
    public String getLearnVideo()
    {
        return video;
    }
    public void setLearnVideo(String video)
    {
        this.video = video;
    }
    @Override
    public String toString()
    {
        return this.getClass()+"[id="+id+",name="+name+",remark="+remark+"]";
    }
}
