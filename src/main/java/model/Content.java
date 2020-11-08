package model;

import annotation.Id;
import annotation.ManyToOne;
import annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table("content")
public class Content implements Serializable {
    @Id
    private int id;
    @ManyToOne(fk = "userId",fkClassName = "model.User")
    private User user;
    @ManyToOne(fk = "videoId",fkClassName = "model.Video")
    private Video video;
    private String content;  //评论内容
    private Date createTime; //评论时间

    public Content() {
    }

    public Content(int id, User user, Video video, String content, Date createTime) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.content = content;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
