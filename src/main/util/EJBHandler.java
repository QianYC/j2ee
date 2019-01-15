package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * @ClassName: EJBHandler
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2019/1/14
 * @Version: 0.0.1
 */
public class EJBHandler {
    private static InitialContext context;
    private static final String PREFIX = "ejb:javaee/";

    public static Object getBean(String beanUrl) throws NamingException {
        if (context == null) {
            Properties properties = new Properties();
            properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            context = new InitialContext(properties);
        }
        return context.lookup(PREFIX + beanUrl);
    }
}
