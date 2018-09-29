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
    private Date lastVisitTime;
    private Double validTime;
    private Long validTimes;
    private String visitPass;


    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

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

    public Double getValidTime() {
        return validTime;
    }

    public void setValidTime(Double validTime) {
        this.validTime = validTime;
    }

    public Long getValidTimes() {
        return validTimes;
    }

    public void setValidTimes(Long validTimes) {
        this.validTimes = validTimes;
    }

    public String getVisitPass() {
        return visitPass;
    }

    public void setVisitPass(String visitPass) {
        this.visitPass = visitPass;
    }

    @Override
    public String toString() {
        return "URL{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", sUrl='" + sUrl + '\'' +
                ", visited=" + visited +
                ", createTime=" + createTime +
                ", validTime=" + validTime +
                ", validTimes=" + validTimes +
                ", visitPass='" + visitPass + '\'' +
                '}';
    }
}
