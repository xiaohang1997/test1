package dao.impl;

import dao.GeneralDao;
import util.DBUtil;
import util.GeneralUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneralDaoImpl<T, PK extends Serializable> implements GeneralDao<T, PK> {
    private Class<T> clazz = null;
    private GeneralUtil<T> generalUtil = new GeneralUtil<T>();

    public GeneralDaoImpl() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        // 获取表名
        String tableName = generalUtil.getTableName(clazz);
        // 表名
        sb.append(tableName);
        //sb.append(" order by id desc"); // id倒序
        // SQL语句
        String sql = sb.toString();
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                // 创建该类型的实例
                T entity = generalUtil.newInstance(clazz, rs);
                // 将该实例添加到集合中
                list.add(entity);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return list;
    }

    @Override
    public boolean save(T entity) {
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        // 获取表名
        String tableName = generalUtil.getTableName((Class<T>) entity.getClass());
        sb.append(tableName);
        sb.append("(");
        // 获取列值
        List<Object> columnValues = new ArrayList<Object>();
        // 获取列名和值
        Map<String, Object> map = generalUtil.getColumnNameAndValue(entity);
        // 遍历Key的集合
        for (String columnName : map.keySet()) {
            sb.append(columnName + ",");
            // 将key对应的值放入集合中
            columnValues.add(map.get(columnName));
        }
        // 去掉最后一个,号
        sb = new StringBuffer(sb.substring(0, sb.length() - 1));
        sb.append(")VALUES(");
        // 添加占位符
        for (int i = 0; i < columnValues.size(); i++) {
            sb.append("?");
            if (i < columnValues.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        // 参数
        Object[] params = columnValues.toArray();
        //System.out.println("SQL语句为：" + sb.toString());
        // 执行
        int rows = DBUtil.executeUpdate(sb.toString(), params);
        return rows>0;
    }

    @Override
    public boolean update(T entity) {
        // SQL语句
        StringBuffer sb = new StringBuffer("UPDATE ");
        // 获取指定对象的Class类
        Class<T> clazz = (Class<T>) entity.getClass();
        // 获取表名
        String tableName = generalUtil.getTableName(clazz);
        // 获取主键名
        String id = generalUtil.getPK(clazz);
        // 获取列名和值
        Map<String, Object> map = generalUtil.getColumnNameAndValue(entity);
        // 列值的集合
        List<Object> columnValues = new ArrayList<Object>();
        // 追加表名
        sb.append(tableName);
        sb.append(" SET ");
        // 遍历
        for (String columnName : map.keySet()) {
            // 判断列名是否为主键列
            if (!columnName.equals(id)) {
                sb.append(columnName);
                sb.append("=?,");
                // 将列值放入集合
                columnValues.add(map.get(columnName));
            }
        }
        sb = new StringBuffer(sb.substring(0, sb.length() - 1));
        // 添加条件
        sb.append(" WHERE ");
        sb.append(id + "=?");
        // 添加主键值
        columnValues.add(map.get(id));
        // SQL语句
        //System.out.println(sb.toString());
        // 参数值
        Object[] params = columnValues.toArray();
        // 执行
        int rows = DBUtil.executeUpdate(sb.toString(), params);
        return rows>0;
    }

    @Override
    public boolean delete(T entity) {
        // 获取表名
        String tableName = generalUtil.getTableName(clazz);
        // 获取主键名
        String pk = generalUtil.getPK(clazz);
        // 获取列名和值
        Map<String, Object> map = generalUtil.getColumnNameAndValue(entity);
        //获取主键值
        Object pkValue = map.get(pk);

        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(tableName);
        sb.append(" WHERE ");
        sb.append(pk + "=?");
        //System.out.println(sb.toString());
        //执行
        int rows = DBUtil.executeUpdate(sb.toString(), pkValue);
        return rows>0;
    }

    @Override
    public T findById(PK id) {
        T entity = null;
        // 获取表名
        String tableName = generalUtil.getTableName(clazz);
        // 获取主键名
        String pk = generalUtil.getPK(clazz);
        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(tableName);
        sb.append(" WHERE ");
        sb.append(pk + "=?");
        //System.out.println(sb.toString());
        ResultSet rs = DBUtil.executeQuery(sb.toString(), id);
        try {
            if (rs.next()) {
                // 创建该类型的实例
                entity = generalUtil.newInstance(clazz, rs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return entity;
    }
    @Override
    public List<T> findByType(Object column,Object params) {
        List<T> list = new ArrayList<T>();
        // 获取表名
        String tableName = generalUtil.getTableName(clazz);
        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(tableName);
        sb.append(" WHERE ");
        sb.append(column + "=?");
        //System.out.println(sb.toString());
        ResultSet rs = DBUtil.executeQuery(sb.toString(), params);
        try {
            while (rs.next()) {
                // 创建该类型的实例
                T entity = generalUtil.newInstance(clazz, rs);
                list.add(entity);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return list;
    }
}

