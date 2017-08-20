package cn.xufx.kn.domain;
import java.util.List;
/**
 * Created by xufuxiu on 2017/8/1.
 */
public class KnowledgePoint
{
    private Integer id;
    private String name;
    private String remark;//简介
    private String content;//定义
    private String memo;//备注
    /*learnGoal：学习目标，取值1,2,3,4,5,6,7,8分别表示记忆、了解、理解、掌握、运用、分析、评价、创造*/
    private String learnGoal;
    private Integer examFrequency;//考试频率，取值1,2,3,4分别表示没考过、偶尔考、经常考、高频考点
    private String reference;//参考资料

    private String doc;//学习文档
    private String video;//学习视频

    private List<Label> labels;//标签
    private List<KnowledgePoint>prekps;
    private List<KnowledgePoint>nextkps;
    private List<KnowledgePoint>relatekps;

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
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content = content;
    }
    public String getMemo()
    {
        return memo;
    }
    public void setMemo(String memo)
    {
        this.memo = memo;
    }
    public String getLearnGoal()
    {
        return learnGoal;
    }
    public void setLearnGoal(String learnGoal)
    {
        this.learnGoal = learnGoal;
    }
    public Integer getExamFrequency()
    {
        return examFrequency;
    }
    public void setExamFrequency(Integer examFrequency)
    {
        this.examFrequency = examFrequency;
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
    public void setDoc(String doc)
    {
        this.doc = doc;
    }
    public String getVideo()
    {
        return video;
    }
    public void setVideo(String video)
    {
        this.video = video;
    }
    public List<Label> getLabels()
    {
        return labels;
    }
    public void setLabels(List<Label> labels)
    {
        this.labels = labels;
    }
    public List<KnowledgePoint> getPrekps()
    {
        return prekps;
    }
    public void setPrekps(List<KnowledgePoint> prekps)
    {
        this.prekps = prekps;
    }
    public List<KnowledgePoint> getNextkps()
    {
        return nextkps;
    }
    public void setNextkps(List<KnowledgePoint> nextkps)
    {
        this.nextkps = nextkps;
    }
    public List<KnowledgePoint> getRelatekps()
    {
        return relatekps;
    }
    public void setRelatekps(List<KnowledgePoint> relatekps)
    {
        this.relatekps = relatekps;
    }
    @Override
    public String toString()
    {
        return "KnowledgePoint[id="+id+",name="+name+",remark="+remark+",content="+content+"]";
    }
}
