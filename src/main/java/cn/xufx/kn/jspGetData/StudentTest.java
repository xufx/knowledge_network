package cn.xufx.kn.jspGetData;
import java.util.*;
/**
 * Created by xufuxiu on 2017/8/9.
 */
public class StudentTest
{
     public static void main(String[] args)
      {
          List<Map<String,Object>> students = new ArrayList<Map<String,Object>>();
          Map<String,Object> s1 = new HashMap<String,Object>();
          s1.put("name","jim");
          s1.put("age","15");
          students.add(s1);
          Map<String,Object> s2 = new HashMap<String,Object>();
          s2.put("name","lucy");
          s2.put("age","12");
          students.add(s2);
      }

}
