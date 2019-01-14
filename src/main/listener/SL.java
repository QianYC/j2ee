package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

/**
 * @ClassName: SL
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/25
 * @Version: 0.0.1
 */
public class SL implements HttpSessionListener, HttpSessionAttributeListener {
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        if (session.getAttribute("userName") != null) {
            ServletContext context = session.getServletContext();
            increaseLogin(context);
            decreaseNonLogin(context);
            System.out.println("login ++");
        }
    }

    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext context = httpSessionEvent.getSession().getServletContext();
        increaseNonLogin(context);
        System.out.println("anonymous ++");
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        if (session.getAttribute("userName") != null) {
            ServletContext context = session.getServletContext();
            decreaseLogin(context);
            System.out.println("login --");
        } else {
            ServletContext context = session.getServletContext();
            decreaseNonLogin(context);
            System.out.println("anonymous --");
        }
        session.invalidate();
    }

    private synchronized void increaseLogin(ServletContext context) {
        Integer integer = (Integer) context.getAttribute("login");
        context.setAttribute("login", integer + 1);
    }

    private synchronized void increaseNonLogin(ServletContext context) {
        Integer integer = (Integer) context.getAttribute("non_login");
        context.setAttribute("non_login", integer + 1);
    }

    private synchronized void decreaseLogin(ServletContext context) {
        Integer integer = (Integer) context.getAttribute("login");
        context.setAttribute("login", integer - 1);
    }

    private synchronized void decreaseNonLogin(ServletContext context) {
        Integer integer = (Integer) context.getAttribute("non_login");
        context.setAttribute("non_login", integer - 1);
    }
}
