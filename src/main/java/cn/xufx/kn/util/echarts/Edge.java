package cn.xufx.kn.util.echarts;
/*边类，包括边的类型、源，目标*/
class Edge
{
    /*边的类型*/
    public static final String SUBORDINATION_TYPE = "subordination";
    public static final String AFFIX_TYPE = "affix";
    public static final String RELATE_TYPE="relate";

    private String type;

    private Integer source;
    private Integer target;

    public Edge() {
    }

    public Edge(String type, Integer source, Integer target)
    {
        this.type = type;
        this.source = source;
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }
}
