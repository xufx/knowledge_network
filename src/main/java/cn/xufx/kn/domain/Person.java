package cn.xufx.kn.domain;
/**Java 用自定义类型作为HashMap的键的domain
 * Created by xufuxiu on 2017/8/10.
 */
public class Person
{
    private String id;
    public Person(String id)
    {
        this.id = id;
    }
    @Override
    public boolean equals(Object o)
    {/*重载equals()是为了向HashMap表明当前对象和key上所保存的对象是相等的*/
        System.out.println("o:"+o);//定义类：包名+类名+地址
        System.out.println("this:"+this);//new时的对象，包名+类名+地址
        System.out.println("getClass():"+getClass());//包名+类名
        System.out.println("o.getClass():"+o.getClass());//包名+类名

        if (this==o)
            return true;
        if (o==null||getClass()!=o.getClass())
            return false;
        Person person=(Person)o;
        System.out.println("id="+id);
        System.out.println("person.id="+person.id);
        if (id!=null ? !id.equals(person.id) : person.id!=null)
        {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode()
    {/*重载hashCode()是为了对同一个key，能得到相同的Hash Code*/
        System.out.println("id:"+id);
        System.out.println("id.hashcode():"+id.hashCode());
        return id!=null?id.hashCode():0;
    }
}
