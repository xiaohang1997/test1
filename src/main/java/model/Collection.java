package model;

import annotation.Id;
import annotation.ManyToOne;
import annotation.Table;

import java.io.Serializable;
@Table("collection")
public class Collection implements Serializable {
    @Id
    private int id;
    @ManyToOne(fk = "userId",fkClassName = "model.User")
    private User user;
    @ManyToOne(fk = "courseId",fkClassName = "model.Course")
    private Course course;

    public Collection() {
    }

    public Collection(int id, User user, Course course) {
        this.id = id;
        this.user = user;
        this.course = course;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
