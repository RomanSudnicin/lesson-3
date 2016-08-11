package servlets;

import services.Context;
import services.accounts.AccountService;
import services.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by roman on 09.08.16.
 */
public class SignUpServlet extends HttpServlet {
    private final Context context;

    public SignUpServlet(Context context) {
        this.context = context;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AccountService accountService = (AccountService) context.get(AccountService.class);
        UserProfile userProfile = new UserProfile();
        userProfile.setId(-1);
        userProfile.setLogin(login);
        userProfile.setPass(password);
        accountService.addNewUser(userProfile);
        resp.getWriter().println("Was added " + login + " " + password);
        resp.setStatus(HttpServletResponse.SC_OK);
        //super.doPost(req, resp);
    }
}
