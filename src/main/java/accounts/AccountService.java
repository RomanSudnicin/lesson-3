package accounts;

import dbService.DBService;
import dbService.dao.UserProfileDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roman on 06.08.16.
 */
public class AccountService {
    private final Map<String, UserProfile> sessionIdToProfile;

    private static AccountService accountService;

    private UserProfileDao userProfileDao;

    private AccountService() {
        userProfileDao = new UserProfileDao(DBService.getDBService().getConnection());
        sessionIdToProfile = new HashMap<>();
    }

    public static AccountService getAccountService() {
        if (accountService == null) {
            accountService = new AccountService();
        }
        return accountService;
    }

    public void addNewUser(UserProfile userProfile) throws SQLException {

        userProfileDao.insertUser(userProfile);

    }

    public void addSession(String session, UserProfile userProfile) {
        sessionIdToProfile.put(session, userProfile);
    }

    public void deleteSession(String session) {
        sessionIdToProfile.remove(session);
    }

    public UserProfile getUserByLogin(String login) throws SQLException {
        return userProfileDao.getUserProfileByLogin(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }
}
