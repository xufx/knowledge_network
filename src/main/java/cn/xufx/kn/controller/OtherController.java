package cn.xufx.kn.controller;
import cn.xufx.kn.domain.Person;
import cn.xufx.kn.service.KNService;
import cn.xufx.kn.service.KPRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
/**
 * Created by xufuxiu on 2017/7/19.
 */
@Controller
public class OtherController
{
    @Autowired
    @Qualifier("kprService")
    private KPRelativeService kprService;
    @Autowired
    @Qualifier("knService")
    private KNService knService;
    /*从jsp页面获得List的测试*/
    @RequestMapping(value = "/jspGet")
    public String jspGetList(Model model)
    {
        List<Map<String, Object>> students = studentTest();
        model.addAttribute("students", students);
        return "kpr/jspGet";
    }

    public void getMap(Map<Integer,Integer> params)
    {/*遍历Map*/
        Set<Map.Entry<Integer,Integer>> sets=params.entrySet();
        for(Map.Entry<Integer,Integer> entry:sets)
        {
            Integer key=entry.getKey();
            Integer value=entry.getValue();
            System.out.println(key+":"+value);
        }
    }
    /*从jsp获得Map的测试，输入http://local：8080/person进行测试*/
    @RequestMapping(value = "/person")
    String jspGetMap(Model model)
    {
        personTest();
        return "kpr/jspGet";/*WEB-INF/jsp/kpr/jspGet.jsp*/
    }
    /*添加、封装student数据*/
    public List<Map<String, Object>> studentTest()
    {
        List<Map<String, Object>> students = new ArrayList<Map<String, Object>>();
        Map<String, Object> s1 = new HashMap<String, Object>();
        s1.put("name", "jim");
        s1.put("age", "15");
        students.add(s1);
        Map<String, Object> s2 = new HashMap<String, Object>();
        s2.put("name", "lucy");
        s2.put("age", "12");
        students.add(s2);
        return students;
    }
    /*添加、封装Person对象*/
    public void personTest()
    {
        HashMap<Person, String> map = new HashMap<Person, String>();
        map.put(new Person("001"), "findingsea");
        map.put(new Person("002"), "linyin");
        map.put(new Person("003"), "henrylin");
        map.put(new Person("003"), "findingsealy");/*重载方法之前不会覆盖*/
        System.out.println(map.toString());

        /*在Person中重载equals方法和hashCode方法之前输出为null*/
        System.out.println(map.get(new Person("001")));
        System.out.println(map.get(new Person("002")));
        System.out.println(map.get(new Person("003")));
    }
}

