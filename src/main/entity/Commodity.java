package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName: Commodity
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/16
 * @Version: 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commodity implements Serializable {
    private String name;
    private double price;

    public String toString(){
        return "name : " + name + ",price : " + price;
    }
}
