package service;



import entity.Commodity;
import entity.User;
import model.Cart;
import model.CartItem;
import model.PurchaseResult;
import repository.CommodityRepository;
import repository.UserRepository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PurchaseService
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2019/1/4
 * @Version: 0.0.1
 */
public class PurchaseService {
    private static PurchaseService service = new PurchaseService();
    private CommodityRepository repository = new CommodityRepository();
    private UserRepository userRepository = new UserRepository();
    private PurchaseService(){}

    public static PurchaseService getInstance() {
        return service;
    }

    public void calculateCost(Cart cart) {
        List<CartItem> items = cart.getItems();
        double cost = 0.0;
        for (CartItem item : items) {
            Commodity commodity = repository.getCommodityByName(item.getName());
            cost += item.getNum() * commodity.getPrice();
            item.setPrice(commodity.getPrice());
        }
        cart.setTotal(cost);
    }

    public PurchaseResult purchase(String userName, Cart cart) {
        PurchaseResult result = new PurchaseResult();
        User user = userRepository.getUserByName(userName);
        double total = cart.getTotal();
        if (user.getMoney() >= total) {
            if (total >= 200) {
                result.setStatus(PurchaseResult.DISCOUNT);
                total *= 0.9;
            } else {
                result.setStatus(PurchaseResult.SUCCESS);
            }
            result.setTotal(total);
            double remain = userRepository.withdraw(userName, total);
            result.setBalance(remain);
        } else {
            result.setStatus(PurchaseResult.FAIL);
        }
        return result;
    }
}
