package cn.xufx.kn.jspGetData;
/**自定义EL函数
 * Created by xufuxiu on 2017/8/11.
 */
public class Functions
{
    public static String reverse(String text)
    {
        String s=new StringBuilder(text).reverse().toString();
        return s;
    }
}
