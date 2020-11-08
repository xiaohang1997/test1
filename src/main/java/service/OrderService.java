package service;

import model.Order;

import java.util.List;

public interface OrderService{
    public List<Order> findAll();
    public boolean save(Order entity);
    public boolean update(Order entity);
    public boolean delete(Order entity);
    public Order findById(int id);
    public List<Order> findByType(Object column, Object prams);
}
