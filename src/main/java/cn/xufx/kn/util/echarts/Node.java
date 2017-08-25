package cn.xufx.kn.util.echarts;
import cn.xufx.kn.domain.KnowledgePoint;
/**节点类型，包括名称、类型、值
 */
class Node
{

    /*节点类型*/
    public static final String CENTER_TYPE = "center";
    public static final String PREFIX_TYPE = "prefix";
    public static final String POSTFIX_TYPE = "postfix";

    public static final String OTHER_TYPE = "other";

    public static final String PARENT_TYPE = "parent";
    public static final String KID_TYPE = "kid";
    public static final String RELATE_type="relate";


    private String name;

    private String type;

    private KnowledgePoint value;

    public Node() {
    }

    public Node(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return getValue() != null ? getValue().equals(node.getValue()) : node.getValue() == null;

    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public KnowledgePoint getValue() {
        return value;
    }

    public void setValue(KnowledgePoint value) {
        this.value = value;
    }
}
