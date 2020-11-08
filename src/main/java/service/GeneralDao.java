package service;

import java.util.List;

/**
 * @param <T> 类型
 * @param <PK> 主键的类型
 */
public interface GeneralDao<T,PK> {
    public List<T> findAll();
    public boolean save(T entity);
    public boolean update(T entity);
    public boolean delete(T entity);
    public T findById(PK id);
    public List<T> findByType(Object column, Object prams);
    //column是数据库列名，prams是传入的搜索条件，例如：findByType("name","张三")
}
