package repository;

import entity.User;
import util.JDBCHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: UserRepository
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/20
 * @Version: 0.0.1
 */
public class UserRepository {
    private DataSource source = JDBCHandler.getDataSource();

    public User getUser(String userName, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        User user = null;

        try {
            connection = source.getConnection();
            statement = connection.prepareStatement("select * from user where userName=? and password=?;");
            statement.setString(1, userName);
            statement.setString(2, password);
            set = statement.executeQuery();
            if (set.next()) {
                user = new User(userName, password, set.getDouble("money"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCHandler.close(set, statement, connection);
        }
        return user;
    }

    public User getUserByName(String userName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        User user = null;

        try {
            connection = source.getConnection();
            statement = connection.prepareStatement("select * from user where userName=?;");
            statement.setString(1, userName);
            set = statement.executeQuery();
            if (set.next()) {
                user = new User(userName, null, set.getDouble("money"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCHandler.close(set, statement, connection);
        }

        return user;
    }

    public double withdraw(String userName, double amount) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("update user set money=money-? where userName=?;");
            statement.setDouble(1, amount);
            statement.setString(2, userName);
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JDBCHandler.close(set, statement, connection);
        }

        return getUserByName(userName).getMoney();
    }
}
