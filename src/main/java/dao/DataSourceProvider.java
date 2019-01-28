package dao;

import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceProvider {
    protected static MariaDbDataSource dataSource;


    public static DataSource getDataSource() throws SQLException {
        if (dataSource == null) {
            dataSource = new MariaDbDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("projetS7");
            dataSource.setUser("root");
            dataSource.setPassword("");
        }
        return dataSource;
    }


}
