package service;

import model.Video;

import java.util.List;

public interface VideoService{
    public List<Video> findAll();
    public boolean save(Video entity);
    public boolean update(Video entity);
    public boolean delete(Video entity);
    public Video findById(int id);
    public List<Video> findByType(Object column, Object prams);
}
