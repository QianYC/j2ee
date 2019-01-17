package util;

import entity.Commodity;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @ClassName: HBNUtil
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2019/1/16
 * @Version: 0.0.1
 */
public class HBNUtil {
    private static SessionFactory factory;

    public static SessionFactory getFactory() {
        if (factory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Commodity.class);
            factory = configuration.buildSessionFactory();
        }
        return factory;
    }

    public static Session getSession() {
        return getFactory().getCurrentSession();
    }
}
