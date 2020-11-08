package service.impl;

import dao.CourseDao;
import dao.impl.CourseDaoImpl;
import model.Course;
import service.CourseService;
import util.DBUtil;
import util.GeneralUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao = new CourseDaoImpl();
    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public boolean save(Course entity) {
        return courseDao.save(entity);
    }

    @Override
    public boolean update(Course entity) {
        return courseDao.update(entity);
    }

    @Override
    public boolean delete(Course entity) {
        return courseDao.delete(entity);
    }

    @Override
    public Course findById(int id) {
        return courseDao.findById(id);
    }

    @Override
    public List<Course> findByType(Object column, Object prams) {
        return courseDao.findByType(column, prams);
    }

    @Override
    public List<Course> findByPriceLimit(int limit) throws SQLException {
        return courseDao.findByPriceLimit(limit);
    }

    @Override
    public List<Course> findByFreePriceLimit(int limit) throws SQLException{
        return courseDao.findByFreePriceLimit(limit);
    }

    @Override
    public List<Course> findHighCourse() {
        GeneralUtil<Course> generalUtil = new GeneralUtil();
        List<Course> courses = new ArrayList<Course>();
        String sql = "select * from course order by number desc limit 0,6";
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()){
                Course course = generalUtil.newInstance(Course.class,rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
