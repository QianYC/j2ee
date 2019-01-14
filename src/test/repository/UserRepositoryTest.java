package repository;

import junit.framework.TestCase;

import java.sql.SQLException;

public class UserRepositoryTest extends TestCase {

    private UserRepository repository = new UserRepository();

    public void testWithdraw() throws SQLException {
        double balance = repository.withdraw("161250103", 100);
        System.out.println(balance);
        balance = repository.withdraw("161250103", -100);
        System.out.println(balance);

    }
}