package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: CartItem
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2019/1/5
 * @Version: 0.0.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private String name;
    private int num;
    private double price;

    public boolean equals(Object item) {return name.equals(((CartItem)item).name);
    }

    public String toString(){
        return "商品名 ：" + name + " 数量 ：" + num + " 价格 ：" + price;
    }
}
