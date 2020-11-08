package service;

import model.Chapter;

import java.util.List;

public interface ChapterService{
    public List<Chapter> findAll();
    public boolean save(Chapter entity);
    public boolean update(Chapter entity);
    public boolean delete(Chapter entity);
    public Chapter findById(int id);
    public List<Chapter> findByType(Object column, Object prams);
}
