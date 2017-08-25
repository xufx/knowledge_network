package cn.xufx.kn.util.echarts;

import java.util.ArrayList;
import java.util.List;
/**定义符合ECharts数据格式规范的对象
 */
public class EChartsProduct
{
    private String name;

    private List<Node> nodes = new ArrayList<Node>();

    private List<Edge> edges = new ArrayList<Edge>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
