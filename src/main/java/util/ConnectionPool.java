package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public final class ConnectionPool {
    private static ConnectionPool connectionPool = null;
    //存放连接的容器--连接池
    private static Vector<Connection> pool = null;

    private static  int poolSize;//连接池的大小
    private static  String driver_class;
    private static  String url;
    private static  String user;
    private static  String password;

    //静态块注册驱动
    static {
        //加载文件，给属性赋值
        load();
        try { //idea自动加载驱动
            Class.forName(driver_class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connectionPool = new ConnectionPool();
    }
    /**
     * 获取连接池类的对象
     * @return
     */
    public static ConnectionPool getInstance() {
        return connectionPool;
    }

    private ConnectionPool() {

        //初始化连接池
        pool = new Vector<Connection>(poolSize);
        //初始化连接
        init();
    }

    private static void load() {
        try {
            //获取输入流对象
            InputStream in = ConnectionPool.class.getResourceAsStream("/db.properties");
            //创建Properties属性对象
            Properties props = new Properties();
            //从输入流中加载数据
            props.load(in);
            //根据键获取值
            driver_class = props.getProperty("jdbc_driver");
            url = props.getProperty("jdbc_url");
            user = props.getProperty("jdbc_user");
            password = props.getProperty("jdbc_password");
            poolSize = Integer.parseInt(props.getProperty("pool_size"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            for(int i=0;i<poolSize;i++) {
                //创建连接对象
                Connection connection = DriverManager.getConnection(url,user,password);
                //将创建好的连接对象放入池中
                pool.add(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 从连接池中获取连接对象
     * @return 连接对象
     */
    public synchronized Connection getConnection() {
        Connection connection = null;
        //判断连接池中是否存在连接
        if(pool.size()>0) {
            //有连接对象，获取一个
            connection = pool.get(0);
            //将该连接从池中移除
            pool.remove(0);
        }else {
            try {
                //创建新的连接对象或抛出异常
                connection = DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    /**
     * 将连接对象放入池中
     * @param connection 连接对象
     */
    public synchronized void release(Connection connection) {
        //将连接对象放入池中
        pool.add(connection);
    }
}
