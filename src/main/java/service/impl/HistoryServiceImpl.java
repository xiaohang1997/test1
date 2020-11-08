package service.impl;

import dao.HistoryDao;
import dao.impl.HistoryDaoImpl;
import model.History;
import service.HistoryService;

import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    private HistoryDao historyDao = new HistoryDaoImpl();
    @Override
    public List<History> findAll() {
        return historyDao.findAll();
    }

    @Override
    public boolean save(History entity) {
        return historyDao.save(entity);
    }

    @Override
    public boolean update(History entity) {
        return historyDao.update(entity);
    }

    @Override
    public boolean delete(History entity) {
        return historyDao.delete(entity);
    }

    @Override
    public History findById(int id) {
        return historyDao.findById(id);
    }

    @Override
    public List<History> findByType(Object column, Object prams) {
        return historyDao.findByType(column, prams);
    }
}
