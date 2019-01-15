package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/15
 * @Version: 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private String userName;
    private String password;
    private double money;
}
