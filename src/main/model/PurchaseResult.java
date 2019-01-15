package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName: PurchaseResult
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2019/1/5
 * @Version: 0.0.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResult implements Serializable {
    public static final int FAIL=0, SUCCESS = 1, DISCOUNT = 2;
    private int status;
    private double total, balance;
}
