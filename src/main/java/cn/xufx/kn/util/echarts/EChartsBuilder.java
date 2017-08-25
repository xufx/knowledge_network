package cn.xufx.kn.util.echarts;

import java.util.*;
/**EChartsBuilder主要持有需要可视化的微知识网络中的节点（知识点）和边（表示知识点之间关联关系的边），然后再进一步处理生成EChartsProduct对象（该对象定义符合ECharts数据格式规范）
 */
public class EChartsBuilder {

    private String name;

    private Set<Integer> node_ids = new HashSet<Integer>();//知识点编号集合

    private Set<Node> nodes = new HashSet<Node>();//知识点对象集合

    private Set<Edge> edges = new HashSet<Edge>();//边对象集合

    public EChartsBuilder(String name) {
        this.name = name;
    }

    /**
     * 去重
     * node:知识点
     */
    public void add(Node node)
    {
        /*将获得的知识点作为节点的值，添加到节点集中*/
        nodes.add(node);
        /*将知识点的id添加到 node_ids列表中，通过node.getValue()获得知识点对象*/
        node_ids.add(node.getValue().getId());
    }

    /**
     * 去重
     * 判断节点编号集合中是否包含知识点id
     */
    public void add(Edge edge) {
        edges.add(edge);
    }

    public boolean contains(Integer id) {
        return node_ids.contains(id);
    }


    public EChartsProduct build()
    {
        /**
         * 对node和edge重新编排，构建有效索引
         */
        List<Node> nodes = new ArrayList<Node>(this.nodes);
        List<Edge> edges = new ArrayList<Edge>(this.edges);

        nodes.sort(new Comparator<Node>()
        {
            @Override
            public int compare(Node o1, Node o2) {
                int id1 = o1.getValue().getId();
                int id2 = o2.getValue().getId();
                return id1 - id2;
            }
        });

        /*indexs:按顺序从小到大存储知识点的id，1,2,3,4.。。*/
        Map<Integer, Integer> indexs = new HashMap<Integer, Integer>();
        for (Node node : nodes)
        {
            int index = nodes.indexOf(node);
            indexs.put(node.getValue().getId(), index);
        }

        /**
         * 转换边的表示：知识点ID ---> node的索引
         */
        for (Edge edge : edges)
        {
            int source = edge.getSource();
            edge.setSource(indexs.get(source));

            int target = edge.getTarget();
            edge.setTarget(indexs.get(target));
        }

        EChartsProduct product = new EChartsProduct();

        product.setName(this.name);
        product.setNodes(nodes);
        product.setEdges(edges);
        System.out.println("product:"+product);
        return product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }
}

