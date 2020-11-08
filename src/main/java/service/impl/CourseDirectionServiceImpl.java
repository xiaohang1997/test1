package service.impl;

import dao.CourseDirectionDao;
import dao.impl.CourseDirectionDaoImpl;
import model.CourseDirection;
import service.CourseDirectionService;

import java.sql.SQLException;
import java.util.List;

public class CourseDirectionServiceImpl implements CourseDirectionService {
    private CourseDirectionDao courseDirectionDao = new CourseDirectionDaoImpl();
    @Override
    public List<CourseDirection> findAll() {
        return courseDirectionDao.findAll();
    }

    @Override
    public boolean save(CourseDirection entity) {
        return courseDirectionDao.save(entity);
    }

    @Override
    public boolean update(CourseDirection entity) {
        return courseDirectionDao.update(entity);
    }

    @Override
    public boolean delete(CourseDirection entity) {
        return courseDirectionDao.delete(entity);
    }

    @Override
    public CourseDirection findById(int id) {
        return courseDirectionDao.findById(id);
    }

    @Override
    public List<CourseDirection> findByType(Object column, Object prams) {
        return courseDirectionDao.findByType(column, prams);
    }

    @Override
    public List<CourseDirection> findCourseDirectionLimit(int limit) throws SQLException {
        return courseDirectionDao.findCourseDirectionLimit(limit);
    }
}
