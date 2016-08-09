package accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roman on 06.08.16.
 */
public class AccountService {
    private final Map<String,UserProfile> loginToProfile;
    private final Map<String,UserProfile> sessionIdToProfile;

    private static AccountService accountService;

    private AccountService (){
        loginToProfile = new HashMap();
        sessionIdToProfile = new HashMap<>();
    }

    public static AccountService getAccountService(){
        if(accountService == null) {
            accountService = new AccountService();
        }
        return accountService;
    }

    public void addNewUser(UserProfile userProfile){
        loginToProfile.put(userProfile.getLogin(),userProfile);
    }

    public void addSession(String session, UserProfile userProfile){
        sessionIdToProfile.put(session, userProfile);
    }

    public void deleteSession(String session){
        sessionIdToProfile.remove(session);
    }

    public UserProfile getUserByLogin(String login){
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId){
        return sessionIdToProfile.get(sessionId);
    }
}
