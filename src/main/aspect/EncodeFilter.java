package aspect;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName: EncodeFilter
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/20
 * @Version: 0.0.1
 */
public class EncodeFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("setting encoding");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
