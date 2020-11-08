package service.impl;

import dao.CollectionDao;
import dao.impl.CollectionDaoImpl;
import model.Collection;
import service.CollectionService;

import java.util.List;

public class CollectionServiceImpl implements CollectionService {
    private CollectionDao collectionDao = new CollectionDaoImpl();
    @Override
    public List<Collection> findAll() {
        return collectionDao.findAll();
    }

    @Override
    public boolean save(Collection entity) {
        return collectionDao.save(entity);
    }

    @Override
    public boolean update(Collection entity) {
        return collectionDao.update(entity);
    }

    @Override
    public boolean delete(Collection entity) {
        return collectionDao.delete(entity);
    }

    @Override
    public Collection findById(int id) {
        return collectionDao.findById(id);
    }

    @Override
    public List<Collection> findByType(Object column, Object prams) {
        return collectionDao.findByType(column, prams);
    }
}
