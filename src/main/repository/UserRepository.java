package repository;

import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HBNUtil;
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

    public User getUser(String userName, String password) {
        Session session = HBNUtil.getSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createQuery("from entity.User  where userName=?1 and password=?2")
                .setParameter(1,userName).setParameter(2,password).getSingleResult();
        transaction.commit();

        return user;
    }

    public User getUserByName(String userName) {
        Session session = HBNUtil.getSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createQuery("from User u where u.userName=?1")
                .setParameter(1, userName).getSingleResult();
        transaction.commit();
        return user;
    }

    public double withdraw(String userName, double amount) {
        Session session = HBNUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("update User set money=money-?1 where userName=?2")
                .setParameter(1,amount).setParameter(2,userName).executeUpdate();
        transaction.commit();

        return getUserByName(userName).getMoney();
    }
}
