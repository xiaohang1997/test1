package dao;

import model.CourseDirection;

import java.sql.SQLException;
import java.util.List;

public interface CourseDirectionDao extends GeneralDao<CourseDirection,Integer> {
    public List<CourseDirection> findCourseDirectionLimit(int limit) throws SQLException;
}
