package service.impl;

import dao.ChapterDao;
import dao.impl.ChapterDaoImpl;
import model.Chapter;
import service.ChapterService;

import java.util.List;

public class ChapterServiceImpl implements ChapterService {
    private ChapterDao chapterDao = new ChapterDaoImpl();
    @Override
    public List<Chapter> findAll() {
        return chapterDao.findAll();
    }

    @Override
    public boolean save(Chapter entity) {
        return chapterDao.save(entity);
    }

    @Override
    public boolean update(Chapter entity) {
        return chapterDao.update(entity);
    }

    @Override
    public boolean delete(Chapter entity) {
        return chapterDao.delete(entity);
    }

    @Override
    public Chapter findById(int id) {
        return chapterDao.findById(id);
    }

    @Override
    public List<Chapter> findByType(Object column, Object prams) {
        return chapterDao.findByType(column, prams);
    }
}
