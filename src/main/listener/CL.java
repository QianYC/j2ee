package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName: CL
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/25
 * @Version: 0.0.1
 */
public class CL implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("login",0);
        context.setAttribute("non_login",0);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        context.removeAttribute("login");
        context.removeAttribute("non_login");
    }
}
