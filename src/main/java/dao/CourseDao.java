package dao;

import model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDao extends GeneralDao<Course,Integer> {
    public List<Course> findByPriceLimit(int limit) throws SQLException;
    public List<Course> findByFreePriceLimit(int limit) throws SQLException;
}
