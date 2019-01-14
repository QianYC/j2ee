package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: LoginResult
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/31
 * @Version: 0.0.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {
    public static final int SUCCESS=0, FAIL = 1;
    private int status;
    private String userName;
    private double money;
}
