package service;

import entity.User;
import model.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.UserRepository;
import util.JDBCHandler;

import javax.sql.DataSource;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/31
 * @Version: 0.0.1
 */
@Component
public class UserService {
//    private static UserService service = new UserService();

    @Autowired
    private UserRepository repository;

//    private UserService() {
//        repository = new UserRepository();
//    }

//    public static UserService getInstance() {
//        return service;
//    }

    public LoginResult login(String userName, String password) {
        LoginResult result = null;
        User user = repository.getUser(userName, password);
        if (user != null) {
            result = new LoginResult(LoginResult.SUCCESS, userName, user.getMoney());
        } else {
            result = new LoginResult(LoginResult.FAIL, null, 0.0);
        }
        return result;
    }
}
