package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName: LoginResult
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2019/1/14
 * @Version: 0.0.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult implements Serializable {
    public static final int SUCCESS = 0, FAIL = 1;
    private int status;
    private String userName;
    private double money;
}
