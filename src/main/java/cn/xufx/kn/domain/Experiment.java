package cn.xufx.kn.domain;
import java.util.List;
/**
 * Created by xufuxiu on 2017/8/1.
 */
public class Experiment
{//仪器
    private Integer id;
    private String name;
    private String remark;//简介
    private String steps;//使用方法
    private String memo;//备注
    private String tip;//注意事项
    private String reference;//参考资料
    private String principle;//实验原理
    private String doc;//学习文档
    private String video;//学习视频
    private List<Instrument>instruments;//实验仪器
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
    public String getSteps()
    {
        return steps;
    }
    public void setSteps(String steps)
    {
        this.steps = steps;
    }
    public String getMemo()
    {
        return memo;
    }
    public void setMemo(String memo)
    {
        this.memo = memo;
    }
    public String getTip()
    {
        return tip;
    }
    public void setTip(String tip)
    {
        this.tip = tip;
    }
    public String getReference()
    {
        return reference;
    }
    public void setReference(String reference)
    {
        this.reference = reference;
    }
    public String getPrinciple()
    {
        return principle;
    }
    public void setPrinciple(String principle)
    {
        this.principle = principle;
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
    public List<Instrument> getInstruments()
    {
        return instruments;
    }
    public void setInstruments(List<Instrument> instruments)
    {
        this.instruments = instruments;
    }
    @Override
    public String toString()
    {
        return this.getClass()+"[id="+id+",name="+name+",remark="+remark+",steps="+steps+",tip="+tip+"]";
    }
}
