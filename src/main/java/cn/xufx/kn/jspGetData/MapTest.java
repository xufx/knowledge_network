package cn.xufx.kn.jspGetData;
import java.util.*;
/**Map遍历练习
 * Created by xufuxiu on 2017/8/10.
 */
public class MapTest
{
    public static void main(String[] args)
    {
        // 创建一个HashMap对象,并加入了一些键值对。
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("111", "java111");
        maps.put("222", "java222");
        maps.put("333", "java333");
        maps.put("444", "java444");
        maps.put("555", "java555");

        // 传统的遍历map集合的方法1; keySet()
        transitionalMethod1(maps);
        // 传统的遍历map集合的方法2; entrySet()
        //traditionalMethod2(maps);
        // 使用增强For循环来遍历map集合方法1; keySet()
        //strongForMethod1(maps);
        // 使用增强For循环来遍历map集合方法2; entrySet()
        //strongForMethod2(maps);
    }
    public static void strongForMethod2(Map<String,String> maps)
    {
        Set<Map.Entry<String,String>>set=maps.entrySet();
        for (Map.Entry<String,String> entry:set)
        {
            String key=entry.getKey();
            String value=entry.getValue();
            System.out.println(key+":"+value);
        }
    }
    private static void strongForMethod1(Map<String,String> maps)
    {
        Set<Map.Entry<String,String>>sets=maps.entrySet();
        Iterator<Map.Entry<String,String>>it=sets.iterator();
        while (it.hasNext())
        {
            Map.Entry<String,String>entry=it.next();
            String key=entry.getKey();
            String value=entry.getValue();
            System.out.println(key+":"+value);
        }
    }
    private static void transitionalMethod1(Map<String,String> maps)
    {
        Set<String>sets=maps.keySet();
        Iterator<String>it=sets.iterator();
        while (it.hasNext())
        {
            String key=it.next();
            String value=maps.get(key);
            System.out.println(key+":"+value);
        }
    }
    public static  void mapGet(Map<Integer,Integer> maps)
    {
        Set<Map.Entry<Integer,Integer>>set=maps.entrySet();
        for (Map.Entry<Integer,Integer> entry:set)
        {
            Integer key=entry.getKey();
            Integer value=entry.getValue();
            System.out.println(key+":"+value);
        }
    }
}
