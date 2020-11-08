package model;

import annotation.Id;
import annotation.ManyToOne;
import annotation.Table;

import java.io.Serializable;
import java.util.Date;
@Table("history")
public class History implements Serializable {  //购买课程历史记录
    @Id
    private int id;
    @ManyToOne(fk = "userId",fkClassName = "model.User")
    private User user;
    @ManyToOne(fk = "courseId",fkClassName = "model.Course")
    private Course course;
    private Date createTime;
}
