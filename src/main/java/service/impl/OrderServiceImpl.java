package service.impl;

import dao.CourseDao;
import dao.OrderDao;
import dao.UserDao;
import dao.impl.CourseDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.UserDaoImpl;
import model.Course;
import model.Order;
import model.User;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private CourseDao courseDao = new CourseDaoImpl();
    @Override
    public List<Order> findAll() {
        List<Order> orders=orderDao.findAll();
        for(Order order:orders){
            User user=userDao.findById(order.getUser().getId());
            order.setUser(user);
            Course course=courseDao.findById(order.getCourse().getId());
            order.setCourse(course);
        }
        return orders;
    }

    @Override
    public boolean save(Order entity) {
        return orderDao.save(entity);
    }

    @Override
    public boolean update(Order entity) {
        return orderDao.update(entity);
    }

    @Override
    public boolean delete(Order entity) {
        return orderDao.delete(entity);
    }

    @Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findByType(Object column, Object prams) {
        return orderDao.findByType(column, prams);
    }
}
