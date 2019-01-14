package controller;

import model.LoginResult;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName: Login
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/14
 * @Version: 0.0.1
 */

@WebServlet("/login")
public class Login extends HttpServlet {

    private UserService service = null;

    public void init() {
        service = UserService.getInstance();
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(60);

        LoginResult result = service.login(userName, password);
        if (result.getStatus()==LoginResult.SUCCESS) {
            request.setAttribute("loginResult", result);
            session.setAttribute("userName", userName);
            getServletContext().getRequestDispatcher(response.encodeURL(request.getContextPath() + "/home.jsp"))
                    .forward(request, response);
        } else{
            getServletContext().getRequestDispatcher(response.encodeURL(request.getContextPath() + "/login_err.jsp"))
                    .forward(request, response);
        }

    }
}
