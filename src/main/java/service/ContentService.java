package service;

import model.Content;
import util.Page;

import java.sql.SQLException;
import java.util.List;

public interface ContentService {
    public List<Content> findAll();
    public boolean save(Content entity);
    public boolean update(Content entity);
    public boolean delete(Content entity);
    public Content findById(int id);
    public List<Content> findByType(Object column, Object prams);
    public Page<Content> findContentsByNowpage(Integer pNumber,Integer videoId) throws SQLException;
    public List<Content> findContentByVideoId(Integer videoId,Integer index,Integer pageSize);
}
