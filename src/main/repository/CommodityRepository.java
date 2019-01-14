package repository;

import entity.Commodity;
import util.JDBCHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CommodityRepository
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/20
 * @Version: 0.0.1
 */
public class CommodityRepository {
    private DataSource source = JDBCHandler.getDataSource();

    synchronized public Commodity getCommodityByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Commodity good = null;
        try {
            connection = source.getConnection();
            statement = connection.prepareStatement("select * from commodity where name = ?;");
            statement.setString(1, name);
            set = statement.executeQuery();
            if (set.next()) {
                good = new Commodity(set.getString("name"), set.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCHandler.close(set, statement, connection);
        }
        return good;
    }

    /**
     * 分页获得commodity对象，page从1开始
     * @param page
     * @param size
     * @return
     */
    synchronized public List<Commodity> getCommodities(int page, int size) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Commodity> list = new ArrayList<Commodity>();
        try {
            connection = source.getConnection();
            statement = connection.prepareStatement("select * from commodity limit ?,?;");
            statement.setInt(1, page-1);
            statement.setInt(2, size);
            set = statement.executeQuery();
            while (set.next()) {
                Commodity tmp = new Commodity(set.getString("name"), set.getDouble("price"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCHandler.close(set, statement, connection);
        }
        return list;
    }

    synchronized public int getPages(int size) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        int count = 0;
        try {
            connection = source.getConnection();
            statement = connection.prepareStatement("select count(*) as count from commodity;");
            set = statement.executeQuery();
            if (set.next()) {
                count = set.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCHandler.close(set, statement, connection);
        }
        return (count - 1) / size + 1;
    }
}
