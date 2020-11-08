package model;

import annotation.Id;
import annotation.ManyToOne;
import annotation.Table;

import java.io.Serializable;
import java.util.Date;
@Table("course")
public class Course implements Serializable {
    @Id
    private int id;
    private String name;
    @ManyToOne(fk = "courseDirectionId",fkClassName = "model.CourseDirection")
    private CourseDirection courseDirection;  //课程方向
    @ManyToOne(fk = "userId",fkClassName = "model.User")
    private User user;
    private String introduce;  //课程简介
    private int price;
    private int number;  //观看人数
    private int level;   //课程难度级别
    private int courseTime;  //课程时长
    private Date createTime;  //创建时间

    public Course() {
    }

    public Course(int id, String name, CourseDirection courseDirection, User user, String introduce, int price, int number, int level, int courseTime, Date createTime) {
        this.id = id;
        this.name = name;
        this.courseDirection = courseDirection;
        this.user = user;
        this.introduce = introduce;
        this.price = price;
        this.number = number;
        this.level = level;
        this.courseTime = courseTime;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CourseDirection getCourseDirection() {
        return courseDirection;
    }

    public void setCourseDirection(CourseDirection courseDirection) {
        this.courseDirection = courseDirection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(int courseTime) {
        this.courseTime = courseTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
