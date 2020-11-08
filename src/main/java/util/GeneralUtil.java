package util;
import annotation.Column;
import annotation.Id;
import annotation.ManyToOne;
import annotation.Table;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class GeneralUtil<T> {
    /**
     * 根据指定的类型获取表名
     * @param clazz Class类
     * @return 表名
     */
    public String getTableName(Class<T> clazz) {
        String tableName = "";
        if(clazz.isAnnotationPresent(Table.class)) {
            //注解类型的值
            tableName = clazz.getDeclaredAnnotation(Table.class).value();
        }else {
            //类名
            tableName = clazz.getSimpleName();
        }
        return tableName;
    }
    /**
     * 获取主键名
     * @param clazz
     * @return
     */
    public String getPK(Class<T> clazz) {
        //获取所有字段
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields ) {
            if(field.isAnnotationPresent(Id.class)) {
                if(field.isAnnotationPresent(Column.class)) {
                    return field.getDeclaredAnnotation(Column.class).value();
                }else {
                    return field.getName();
                }
            }
        }
        return null;
    }

     //获取所有的列名及属性值
    public Map<String,Object> getColumnNameAndValue(T entity){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            //获取指定对象的类
            Class<T> clazz = (Class<T>) entity.getClass();
            //获取所有字段
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields ) {
                //列名
                String columnName = null;
                //设置字段可访问性
                field.setAccessible(true);
                //获取字段的值
                Object value = field.get(entity);
                if(field.isAnnotationPresent(Column.class)) {
                    //列名 为 注解指定的名称
                    columnName = field.getDeclaredAnnotation(Column.class).value();
                }else if(field.isAnnotationPresent(ManyToOne.class)){
                    //获取注解对象
                    ManyToOne manyToOne = field.getDeclaredAnnotation(ManyToOne.class);
                    //列名
                    columnName = manyToOne.fk();
                    //引用的类名
                    String fkClassName = manyToOne.fkClassName();
                    //引用类
                    Class fkClazz = Class.forName(fkClassName);
                    //获取主键列
                    String pk = getPK(fkClazz);
                    //获取主键字段
                    Field fkField = fkClazz.getDeclaredField(pk);
                    //设置fkField的可访问性
                    fkField.setAccessible(true);
                    //获取引用的表的主键值
                    value = fkField.get(value);
                }else {
                    //列名 为 字段名
                    columnName = field.getName();
                }
                //添加到Map中
                map.put(columnName, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 根据指定的类和结果集获取对象
     * @param clazz
     * @param rs
     * @return
     */
    public T newInstance(Class<T> clazz,ResultSet rs) {
        T entity = null;
        try {
            entity = clazz.newInstance();
            //获取该类型的所有字段
            Field[] fields = clazz.getDeclaredFields();
            //遍历
            for(Field field : fields) {
                //获取列名
                String columnName = "";
                if(field.isAnnotationPresent(Column.class)) {
                    columnName = field.getDeclaredAnnotation(Column.class).value();
                    //从结果集中获取列值
                    Object columnValue = rs.getObject(columnName);
                    //设置对象的属性的可访问性
                    field.setAccessible(true);
                    //给对象的属性赋值
                    field.set(entity, columnValue);
                }else if(field.isAnnotationPresent(ManyToOne.class)){
                    //获取注解对象
                    ManyToOne manyToOne = field.getDeclaredAnnotation(ManyToOne.class);
                    //列名
                    columnName = manyToOne.fk();
                    //引用的类名
                    String fkClassName = manyToOne.fkClassName();
                    //引用类
                    Class fkClazz = Class.forName(fkClassName);
                    //从结果集中获取外键值
                    Object fkValue = rs.getObject(columnName);
                    //创建外键对象
                    Object fkObj = fkClazz.newInstance();
                    //获取主键列
                    String pk = getPK(fkClazz);
                    //获取主键字段
                    Field fkField = fkClazz.getDeclaredField(pk);
                    //设置fkField的可访问性
                    fkField.setAccessible(true);
                    //设置值
                    fkField.set(fkObj, fkValue);
                    //设置对象的属性的可访问性
                    field.setAccessible(true);
                    //给对象的属性赋值
                    field.set(entity, fkObj);
                }else {
                    columnName = field.getName();
                    //从结果集中获取列值
                    Object columnValue = rs.getObject(columnName);
                    //设置对象的属性的可访问性
                    field.setAccessible(true);
                    //给对象的属性赋值
                    field.set(entity, columnValue);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return entity;
    }
}

