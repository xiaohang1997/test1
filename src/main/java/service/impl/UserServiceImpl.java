package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean save(User entity) {
        return userDao.save(entity);
    }

    @Override
    public boolean update(User entity) {
        return userDao.update(entity);
    }

    @Override
    public boolean delete(User entity) {
        return userDao.delete(entity);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findByType(Object column, Object prams) {
        return userDao.findByType(column, prams);
    }

    @Override
    public List<User> findTeacherLimit(int limit) throws SQLException{
        return userDao.findTeacherLimit(limit);
    }

    @Override
    public boolean login(User user) {
        List<User> list = userDao.findByType("loginName",user.getLoginName());
        if(list!=null){
            if(list.get(0).getLoginPwd().equals(user.getLoginPwd())&&list.get(0).getIdentify()==user.getIdentify()){
                return true;
            }
        }
        return false;
    }
}
