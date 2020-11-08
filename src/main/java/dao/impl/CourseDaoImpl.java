package dao.impl;

import dao.CourseDao;
import model.Course;
import util.DBUtil;
import util.GeneralUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl extends GeneralDaoImpl<Course,Integer> implements CourseDao {
    private GeneralUtil<Course> generalUtil = new GeneralUtil<Course>();
    @Override
    public List<Course> findByPriceLimit(int limit) throws SQLException {
        List<Course> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer("select * from course order by price desc limit 0,");
        String sql = sb.append(limit).toString();
        ResultSet re = DBUtil.executeQuery(sql);
        while (re.next()){
            Course course = generalUtil.newInstance(Course.class,re);
            list.add(course);
        }
        return list;
    }
    public List<Course> findByFreePriceLimit(int limit) throws SQLException {
        List<Course> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer("select * from course where price = 0 limit 0,");
        String sql = sb.append(limit).toString();
        ResultSet re = DBUtil.executeQuery(sql);
        while (re.next()){
            Course course = generalUtil.newInstance(Course.class,re);
            list.add(course);
        }
        return list;
    }
}
