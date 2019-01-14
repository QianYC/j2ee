package aspect;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @ClassName: AuthFilter
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/20
 * @Version: 0.0.1
 */
//@WebFilter("/*")
//在web.xml中定义filter和url-pattern 和此注解效果相同
public class AuthFilter implements Filter {

    private String name;

    public void init(FilterConfig filterConfig) throws ServletException {
        name = filterConfig.getInitParameter("fname");
        System.out.println(name + " is initializing");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter " + ((HttpServletRequest) servletRequest).getRequestURI());
        HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
        String path = ((HttpServletRequest) servletRequest).getServletPath();
        if ((session == null || session.getAttribute("userName") == null)
                && !path.equals("/index.jsp") && !path.equals("/login")) {
            session.getServletContext().getRequestDispatcher(((HttpServletResponse) servletResponse)
                    .encodeURL(((HttpServletRequest) servletRequest).getContextPath() + "/index.jsp"))
                    .forward(servletRequest, servletResponse);
            System.out.println("[AuthFilter] : 用户未登录");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}
