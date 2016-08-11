package services.dbService.dao;

import services.accounts.UserProfile;
import services.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by roman on 10.08.16.
 */
public class UserProfileDao {
    private Executor executor;

    public UserProfileDao(Connection connection) {
        executor = new Executor(connection);
    }

    public void insertUser(UserProfile userProfile) throws SQLException {
        executor.execUpdate("insert into user_table (login,password) values ('" + userProfile.getLogin() + "','" + userProfile.getPass() + "')");
    }

    public UserProfile getUserProfileByLogin(String login) throws SQLException {
        return executor.execQuery("select * from user_table where login='" + login + "'", result -> {
            result.next();
            UserProfile userProfile = new UserProfile(result.getString(2), result.getString(3), "");
            return userProfile;
        });
    }
}
