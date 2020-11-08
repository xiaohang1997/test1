package model;


import annotation.Id;
import annotation.ManyToOne;
import annotation.Table;

import java.io.Serializable;

@Table("chapter")
public class Chapter implements Serializable {  //课程下属章节
    @Id
    private int id;
    private String name;
    @ManyToOne(fk = "courseId",fkClassName = "model.Course")
    private Course course;
    @ManyToOne(fk = "videoId",fkClassName = "model.Video")
    private Video video;

    public Chapter() {
    }

    public Chapter(int id, String name, Course course, Video video) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
