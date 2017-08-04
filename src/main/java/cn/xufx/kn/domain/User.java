package cn.xufx.kn.domain;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by xufuxiu on 2017/7/18.
 */
public class User implements Serializable
{
    private Integer id;
    private String username;
    private String loginname;
    private String password;
    private String status;
    private Date createDate;
    public User()
    {
        super();
    }
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getLoginname()
    {
        return loginname;
    }
    public void setLoginname(String loginname)
    {
        this.loginname = loginname;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public Date getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", loginname="
                + loginname + ", password=" + password + ", status=" + status
                + ", createDate=" + createDate + "]";
    }
}
