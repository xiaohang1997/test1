package model;

import annotation.Id;
import annotation.Table;

import java.io.Serializable;
@Table("courseDirection")
public class CourseDirection implements Serializable {
    @Id
    private int id;
    private String name;

    public CourseDirection() {
    }

    public CourseDirection(int id, String name) {
        this.id = id;
        this.name = name;
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
}
