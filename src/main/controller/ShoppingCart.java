package controller;

import model.Cart;
import model.PurchaseResult;
import service.PurchaseService;
import util.EJBHandler;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName: ShoppingCart
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2019/1/4
 * @Version: 0.0.1
 */
@WebServlet("/cart")
public class ShoppingCart extends HttpServlet {
    @EJB
    private PurchaseService service;
    
    public void init(){
//        service = PurchaseService.getInstance();
//        try {
//            service = (PurchaseService) EJBHandler.getBean("PurchaseServiceImpl!service.PurchaseService");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");
        assert session != null && cart != null;
        cart=service.calculateCost(cart);
        session.setAttribute("cart", cart);
//        System.out.println(cart);
//        System.out.println(cart.getTotal());

        getServletContext().getRequestDispatcher(response.encodeURL(request.getContextPath() + "/cart.jsp"))
                .forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        assert session != null;
        Cart cart = (Cart) session.getAttribute("cart");
        assert cart != null;
        String userName = (String) session.getAttribute("userName");
        PurchaseResult result = service.purchase(userName, cart);
        request.setAttribute("purchaseResult", result);
        getServletContext().getRequestDispatcher(response.encodeURL(request.getContextPath() + "/purchaseResult.jsp"))
                .forward(request, response);
        //点击购买后不管成功与否均要清空购物车
        session.removeAttribute("cart");
    }
}
