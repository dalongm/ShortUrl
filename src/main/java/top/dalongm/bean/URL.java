package top.dalongm.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @author dalongm
 * @description
 * @date 2018/9/28 14:57
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class URL {
    private Long id;
    private String url;
    private String sUrl;
    private Long visited;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }

    public Long getVisited() {
        return visited;
    }

    public void setVisited(Long visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "URL{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", sUrl='" + sUrl + '\'' +
                ", visited=" + visited +
                ", createTime=" + createTime +
                '}';
    }
}
