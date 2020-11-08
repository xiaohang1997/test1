package dao.impl;

import dao.UserDao;
import model.User;
import util.DBUtil;
import util.GeneralUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends GeneralDaoImpl<User,Integer> implements UserDao {
    private GeneralUtil<User> generalUtil = new GeneralUtil<>();
    @Override
    public List<User> findTeacherLimit(int limit) throws SQLException {
        List<User> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer("select * from user where identify=1 limit 0,");
        String sql = sb.append(limit).toString();
        ResultSet re = DBUtil.executeQuery(sql);
        while (re.next()){
            User user = generalUtil.newInstance(User.class,re);
            list.add(user);
        }
        return list;
    }
}
