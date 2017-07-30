package cn.xu.kn.util.tag;
import org.fkit.hrm.util.common.HrmConstants;
/**
 * Created by xufuxiu on 2017/7/18.
 */
public class PageModel
{
    private  int recordCount;//总数据条数，通过dao.count()获得
    private int pageIndex;//当前页面，通过前端jsp页面获取

    //每页分多少条数据，由前端jsp页面指定
    private int pageSize= HrmConstants.PAGE_DEFAULT_SIZE=4;
    private int totalSize;//总页数
    public int getRecordCount()
    {// 数据条数，
        this.recordCount=this.recordCount<=0?0:this.recordCount;
        return recordCount;
    }
    public void setRecordCount(int recordCount)
    {
        this.recordCount = recordCount;
    }
    public int getPageIndex()
    {//当前页面
        this.pageIndex=this.getPageSize()<=0?1:this.pageIndex;
        /*判断当前页面是否超过了总页数，如果超过了就把最后一页作为当前页*/
        this.pageIndex=this.pageIndex>=this.getTotalSize()?this.getTotalSize():this.pageIndex;
        return pageIndex;
    }
    public void setPageIndex(int pageIndex)
    {
        this.pageIndex = pageIndex;
    }
    public int getPageSize()
    {//每页的记录数
        this.pageSize=this.pageSize<=HrmConstants.PAGE_DEFAULT_SIZE?HrmConstants.PAGE_DEFAULT_SIZE:this.pageSize;
        return pageSize;
    }
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    public int getTotalSize()
    {
        /*计算总页数，如果数据条数《=0，总页数为0，否则总页数=（数据条数-1）/每页记录数+1 */
        if (this.getRecordCount()<=0)totalSize=0;
        else totalSize=(this.getRecordCount()-1)/this.getPageSize()+1;
        return totalSize;
    }
    public void setTotalSize(int totalSize)
    {
        this.totalSize = totalSize;
    }
    public  int getFirstLimitParam()
    {
        return (this.getPageIndex()-1)*this.getPageSize();
    }
    @Override
    public String toString()
    {
        return "[recordCount="+recordCount+",pageIndex="+pageIndex+",pageSize="+pageSize+",totalSize="+totalSize+"]";
    }
}
