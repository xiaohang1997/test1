package service.impl;

import dao.VideoDao;
import dao.impl.VideoDaoImpl;
import model.Video;
import service.VideoService;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    private VideoDao videoDao =new VideoDaoImpl();
    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public boolean save(Video entity) {
        return videoDao.save(entity);
    }

    @Override
    public boolean update(Video entity) {
        return videoDao.update(entity);
    }

    @Override
    public boolean delete(Video entity) {
        return videoDao.delete(entity);
    }

    @Override
    public Video findById(int id) {
        return videoDao.findById(id);
    }

    @Override
    public List<Video> findByType(Object column, Object prams) {
        return videoDao.findByType(column, prams);
    }
}
