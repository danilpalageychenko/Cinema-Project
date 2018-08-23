package dblib;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import helpers.PropertyHolder;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by gaara on 18.09.16.
 */
public final class ConnectionsPool {

    private static ComboPooledDataSource poolConnections;
    private static ConnectionsPool dataSource;

    private ConnectionsPool(){initPoolConnections();}

    public static synchronized ConnectionsPool getInstance()
    {
        if(dataSource == null)
        {
            dataSource = new ConnectionsPool();
        }
        return dataSource;
    }

    public Connection getConnection()
    {
        Connection connection = null;
        try {
            connection = poolConnections.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    private static void initPoolConnections()
    {
        poolConnections = new ComboPooledDataSource();
        PropertyHolder propertyHolder = PropertyHolder.getInstance();

        try {
            poolConnections.setDriverClass(propertyHolder.getDbDriver());
            poolConnections.setJdbcUrl(propertyHolder.getJdbcURL());
            poolConnections.setUser(propertyHolder.getDbUserLogin());
            poolConnections.setPassword(propertyHolder.getDbUserPassword());

            poolConnections.setMinPoolSize(5);
            poolConnections.setAcquireIncrement(1);
            poolConnections.setMaxPoolSize(100);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}