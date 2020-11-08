package dao.impl;

import dao.CourseDirectionDao;
import model.CourseDirection;
import util.DBUtil;
import util.GeneralUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDirectionDaoImpl extends GeneralDaoImpl<CourseDirection,Integer> implements CourseDirectionDao {
    private GeneralUtil<CourseDirection> generalUtil = new GeneralUtil<>();

    @Override
    public List<CourseDirection> findCourseDirectionLimit(int limit) throws SQLException{
        List<CourseDirection> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer("select * from courseDirection limit 0,");
        String sql = sb.append(limit).toString();
        ResultSet re = DBUtil.executeQuery(sql);
        while (re.next()){
            CourseDirection courseDirection = generalUtil.newInstance(CourseDirection.class,re);
            list.add(courseDirection);
        }
        return list;
    }
}
