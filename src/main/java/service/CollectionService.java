package service;

import model.Collection;

import java.util.List;

public interface CollectionService{
    public List<Collection> findAll();
    public boolean save(Collection entity);
    public boolean update(Collection entity);
    public boolean delete(Collection entity);
    public Collection findById(int id);
    public List<Collection> findByType(Object column, Object prams);
}
