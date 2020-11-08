package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DBUtil{
    // 连接池类的实例
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();
    // 连接对象
    private static Connection connection;

    private DBUtil() {

    }

    /**
     * 执行DML操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;
        // 获取连接对象
        connection = connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rows = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return rows;
    }

    /**
     * 执行DQL操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static ResultSet executeQuery(String sql, Object... params) {
        ResultSet rs = null;
        // 获取连接对象
        connection = connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    /**
     * 释放连接
     */
    public static void close() {
        // 将连接对象放入池中
        connectionPool.release(connection);
    }
}
