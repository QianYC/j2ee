package repository;

import entity.Commodity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: CommodityRepository
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/20
 * @Version: 0.0.1
 */
@Repository
public class CommodityRepository {

    @Autowired
    SessionFactory factory;

    public Commodity getCommodityByName(String name) {
        Session session = factory.getCurrentSession();
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
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Commodity> list = session.createQuery("from Commodity")
                .setFirstResult((page - 1) * size).setMaxResults(size)
                .getResultList();
        transaction.commit();
        return list;
    }

    public int getPages(int size) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Long count = (Long) session.createQuery("select count(c) from Commodity c")
                .getSingleResult();
        transaction.commit();
        return (int) ((count - 1) / size + 1);
    }
}
