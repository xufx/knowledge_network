var itemStyle_center = {
    normal: {
        color: 'blue'
    },
    emphasis: {}
};

var itemStyle_prefix = {
    normal: {
        color: 'green'
    },
    emphasis: {}
};

var itemStyle_postfix = {
    normal: {
        color: 'green'
    },
    emphasis: {}
};

var itemStyle_relate = {
    normal: {
        color: 'brown'
    },
    emphasis: {}
};


var edge_label_affix = {
    normal: {
        show: true,
        textStyle: {
            fontWeight: 'bold'
        },
        formatter: ''
    },
    emphasis: {
        show: true,
        textStyle: {
            fontWeight: 'bolder'
        },
        formatter: ''
    }
};

var edge_label_subordination = {
    normal: {
        show: true,
        textStyle: {
            fontWeight: 'bold'
        },
        formatter: ''
    },
    emphasis: {
        show: true,
        textStyle: {
            fontWeight: 'bolder'
        },
        formatter: ''
    }
};

var tooltip_global = {
    trigger: 'item',
    triggerOn: 'click',
    enterable: true,//鼠标是否可进入提示框浮层中
    position: 'right',//提示框浮层的位置，默认不设置时位置会跟随鼠标的位置。
    hideDelay: 50,
    formatter: function (params, ticket, callback) //提示框浮层内容
    {
        var contextPath = "";
        var content = '<div>'
            + 'name:&nbsp;' + params.data.name + '<br/>';
        content += 'description:<br/>'
            + '<p>&nbsp;&nbsp;' + params.data.value.description + '</p>';
        content +=
            '<a href="'
            + contextPath + '/knowledge-point/show?kp_id='
            + params.data.value.id + '">' + '查看详情</a></div>';
        return content;
    }
};

/*前、后续知识点的边*/
var lineStyle_affix = {
    normal: {
        opacity: 0.6,
        width: 3,
        type: 'solid'
    },
    emphasis: {
        opacity: 1,
        width: 6,
        type: 'solid'
    },
};

/*知识点的边用虚线表示*/
var lineStyle_subordination = {
    normal: {
        /*opacity: 0.6,*/
        width: 3,
        type: 'solid',
        color:'yellow'
    }
};

function node_forEach(myChart, node) {
    console.log("node.type:"+node.type);
    switch (node.type) {
        case "center":
            node.symbol = "rect";
            node.symbolSize = 50;
            node.itemStyle = itemStyle_center;
            node.x = myChart.getWidth() / 2;
            node.y = myChart.getHeight() / 2;
            break;
        case "prefix":
            node.itemStyle = itemStyle_prefix;
            break;
        case "postfix":
            node.itemStyle = itemStyle_postfix;
            break;
        case "relate":
            node.itemStyle = itemStyle_relate;
            break;
        /*case "kid":
         node.itemStyle = itemStyle_kid;
         break;*/
    }
};

function edge_forEach(edge) {
    console.log("edge.type:"+edge.type);
    if (edge.type == "affix")
    {
        edge.label = edge_label_affix;
        edge.lineStyle = lineStyle_affix;
    } else if (edge.type == "relate")
    {
        edge.label = edge_label_subordination;
        edge.lineStyle = lineStyle_subordination;
    }
};

/*mychart:jsp中的元素
* subnet：Controller返回的json数据*/
function kp_echarts(myChart, subnet)
{
    //alert("进入kp_echarts");
    var name="";
    var nodes=[];
    var edges=[];
    if (subnet == null) {
        return;
    }
    /*将json字符串subnet转化为json对象*/
    var obj=eval('('+subnet+')');
    for(var str in obj)
    {
        if (str=="edges")
        {
            for(var edge in obj[str])
            {
                var o=obj[str][edge];
                edges.push(o);
                edge_forEach(o);
            }
        }else if(str=="name")
        {
            //console.log(str+":"+obj[str]);//字符串
            name=obj[str];

        }else if (str=="nodes")
        {
            obj[str].forEach(function (node)
            {
                nodes.push(node);
                node_forEach(myChart, node);
            });
        }
    }
    option = {
        title: {
            text: name,
            subtext: 'Default layout',
            top: 'bottom',
            left: 'right'
        },
        tooltip: tooltip_global,

        series: [{
            type: 'graph',
            layout: 'force',
            animation: true,
            roam: true,
            draggable: true,
            focusNodeAdjacency: true,

            symbol: 'circle',//关系图节点标记的图形
            symbolSize: 50,
            itemStyle: {
                normal: {},//图形在默认状态下的样式
                emphasis: {}//图形在高亮状态下的样式
            },

            edgeSymbol: ['circle', 'arrow'],
            edgeSymbolSize: [4, 10],

            lineStyle: {//线条样式
                normal: {
                    opacity: 0.6,
                    width: 3
                },
                emphasis: {
                    opacity: 1,
                    width: 6
                },
            },

            label: {
                normal: {
                    position: 'inside',
                    show: true
                },
                emphasis: {
                    position: 'inside',
                    show: true
                }
            },

            force: {
                edgeLength: [50, 120],//边的两个节点之间的距离
                repulsion: 120,//节点之间的斥力因子。
                gravity: 0,//节点受到的向中心的引力因子。该值越大节点越往中心点靠拢。
                layoutAnimation: true
            },

            nodes: nodes,
            edges: edges
        }]
    };
    myChart.setOption(option);

};



