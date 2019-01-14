package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName: JDBCHandler
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/15
 * @Version: 0.0.1
 */
public class JDBCHandler {
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static DataSource source=null;

    public static DataSource getDataSource() {
        try {
            Context ctx = new InitialContext();
            if (ctx != null && source == null) {
                source = (DataSource) ctx.lookup("java:comp/env/jdbc/javaee");
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return source;
    }
}
