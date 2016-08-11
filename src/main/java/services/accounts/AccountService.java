package services.accounts;

import services.Context;
import services.dbService.DBService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roman on 06.08.16.
 */
public class AccountService {
    private final Map<String, UserProfile> sessionIdToProfile;

    private static AccountService accountService;
    private final DBService dbService;


    private AccountService(Context context) {
        sessionIdToProfile = new HashMap<>();
        dbService = (DBService) context.get(DBService.class);
    }


    public void addNewUser(UserProfile userProfile) throws SQLException {

        dbService.addUserProfile(userProfile);

    }

    public void addSession(String session, UserProfile userProfile) {
        sessionIdToProfile.put(session, userProfile);
    }

    public void deleteSession(String session) {
        sessionIdToProfile.remove(session);
    }

    public UserProfile getUserByLogin(String login) throws SQLException {
        return dbService.getUserProfileByLogin(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }
}
