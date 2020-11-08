package model;

import annotation.Id;
import annotation.ManyToOne;
import annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table("orders")
public class Order implements Serializable {
    @Id
    private int id;
    @ManyToOne(fk = "userId",fkClassName = "model.User")
    private User user;
    @ManyToOne(fk = "courseId",fkClassName = "model.Course")
    private Course course;
    private int status;  //订单状态，0未付，1已付
    private Date date;
    private String orderNum;

    public Order() {
    }

    public Order(int id, User user, Course course, int status, Date date, String orderNum) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.status = status;
        this.date = date;
        this.orderNum = orderNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
