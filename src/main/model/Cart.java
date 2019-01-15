package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Cart
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2019/1/4
 * @Version: 0.0.1
 */
@Getter
@Setter
public class Cart implements Serializable {
    private double total;
    private List<CartItem> items = new ArrayList<CartItem>();

    public void addProduct(String pname, int pnum) {
        final CartItem item = new CartItem(pname, pnum, 0.0);
        if (items.contains(item)) {
            System.out.println("exists!!!");
            items.stream().filter(x -> x.equals(item)).forEach(x -> x.setNum(x.getNum() + pnum));
        } else {
            items.add(item);
        }
    }

    

    public String toString(){
        return items.toString();
    }
}
