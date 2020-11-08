package service;

import model.History;

import java.util.List;

public interface HistoryService{
    public List<History> findAll();
    public boolean save(History entity);
    public boolean update(History entity);
    public boolean delete(History entity);
    public History findById(int id);
    public List<History> findByType(Object column, Object prams);
}
