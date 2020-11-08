package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends GeneralDao<User,Integer> {
    public List<User> findTeacherLimit(int limit) throws SQLException;
}
