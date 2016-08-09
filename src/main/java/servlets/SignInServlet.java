package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by roman on 09.08.16.
 */
public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AccountService accountService = AccountService.getAccountService();
        UserProfile userProfile = accountService.getUserByLogin(login);

        if(userProfile == null || !userProfile.getPass().equals(password)){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().print("Unauthorized");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().print("Authorized: " + userProfile.getLogin());
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
