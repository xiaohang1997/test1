package service.impl;

import dao.ContentDao;
import dao.impl.ContentDaoImpl;
import model.Content;
import service.ContentService;
import util.DBUtil;
import util.GeneralUtil;
import util.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContentServiceImpl implements ContentService {
    private ContentDao contentDao = new ContentDaoImpl();
    @Override
    public List<Content> findAll() {
        return contentDao.findAll();
    }

    @Override
    public boolean save(Content entity) {
        return contentDao.save(entity);
    }

    @Override
    public boolean update(Content entity) {
        return contentDao.update(entity);
    }

    @Override
    public boolean delete(Content entity) {
        return contentDao.delete(entity);
    }

    @Override
    public Content findById(int id) {
        return contentDao.findById(id);
    }

    @Override
    public List<Content> findByType(Object column, Object prams) {
        return contentDao.findByType(column, prams);
    }

    @Override
    public Page<Content> findContentsByNowpage(Integer pNumber,Integer videoId) throws SQLException {
        GeneralUtil generalUtil = new GeneralUtil();
        Page<Content> page = new Page<Content>();
        Integer count = page.count("content");
        page.setParams(count,pNumber,2);
        if(page.getPages()==1){
            page.setPageNumber(-2);
        }else if(page.getPages()==2){
            page.setPageNumber(-1);
        }
        List<Content> contents = new ArrayList<Content>();
        contents = findContentByVideoId(videoId,(pNumber-1)*2,2);
        page.setList(contents);
        return page;
    }

    @Override
    public List<Content> findContentByVideoId(Integer videoId,Integer index,Integer pageSize) {
        GeneralUtil<Content> generalUtil = new GeneralUtil();
        List<Content> list = new ArrayList<Content>();
        String sql = "select * from content where videoId=? order by createTime desc limit "+index+" , "+pageSize;
        ResultSet rs = DBUtil.executeQuery(sql,videoId);
        try {
            while(rs.next()){
                Content content = generalUtil.newInstance(Content.class,rs);
                list.add(content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }
}
