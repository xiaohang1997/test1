package service;

import model.CourseDirection;

import java.sql.SQLException;
import java.util.List;

public interface CourseDirectionService {
    public List<CourseDirection> findAll();
    public boolean save(CourseDirection entity);
    public boolean update(CourseDirection entity);
    public boolean delete(CourseDirection entity);
    public CourseDirection findById(int id);
    public List<CourseDirection> findByType(Object column, Object prams);
    public List<CourseDirection> findCourseDirectionLimit(int limit) throws SQLException;
}
