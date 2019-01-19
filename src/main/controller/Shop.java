package controller;

import model.Cart;
import model.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @ClassName: Shop
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/16
 * @Version: 0.0.1
 */

@WebServlet("/shop")
public class Shop extends HttpServlet {

    @Autowired
    private ProductService productService;

    public void init(){
//        productService = ProductService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int size = 5;
        int page = getPage(request);
        int maxPage=productService.getMaxPage(size);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);

        HttpSession session = request.getSession(false);
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Cart());
        }

        ProductList list = productService.getProductList(page,size);
        request.setAttribute("list", list);
        getServletContext().getRequestDispatcher(response.encodeURL(request.getContextPath()+"/market.jsp"))
                .forward(request, response);
    }

    private int getPage(HttpServletRequest request) {
        String pstr = request.getParameter("page");
        return pstr == null || pstr.length() == 0 ? 1 : Integer.parseInt(pstr);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        assert session != null;
        Cart cart = (Cart) session.getAttribute("cart");
        Enumeration<String> products = request.getParameterNames();
        while (products.hasMoreElements()) {
            String pname = products.nextElement();
            System.out.println("pname = " + pname);
            if (!pname.equals("page")) {
                //request还有page参数
                try {
                    int pnum = parseNum(request.getParameter(pname));
                    cart.addProduct(pname, pnum);
                } catch (NumberFormatException e) {
                    getServletContext().getRequestDispatcher(response.encodeURL(request.getContextPath() + "/market_err.jsp"))
                            .forward(request, response);
                }
            }
        }
        session.setAttribute("cart", cart);
//        System.out.println(cart);
        doGet(request, response);
    }

    private int parseNum(String num) {
        if (num.equals("")) {
            return 0;
        } else {
            int res = Integer.parseInt(num);
            if (res < 0) {
                throw new NumberFormatException("negative quantity");
            }
            return res;
        }
    }
}
