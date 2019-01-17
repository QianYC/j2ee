package repository;

import entity.Commodity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HBNUtil;
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

    public Commodity getCommodityByName(String name) {
        Session session = HBNUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Commodity commodity = (Commodity) session.createQuery("from Commodity where name=?1")
                .setParameter(1, name).getSingleResult();
        transaction.commit();
        return commodity;
    }

    /**
     * 分页获得commodity对象，page从1开始
     * @param page
     * @param size
     * @return
     */
    public List<Commodity> getCommodities(int page, int size) {
        Session session = HBNUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<Commodity> list = session.createQuery("from Commodity")
                .setFirstResult((page - 1) * size).setMaxResults(size)
                .getResultList();
        transaction.commit();
        return list;
    }

    public int getPages(int size) {
        Session session = HBNUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Long count = (Long) HBNUtil.getSession().createQuery("select count(c) from Commodity c")
                .getSingleResult();
        transaction.commit();
        return (int) ((count - 1) / size + 1);
    }
}
