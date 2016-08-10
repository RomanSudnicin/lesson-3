package servlets;

import accounts.AccountService;
import accounts.UserProfile;

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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AccountService accountService = AccountService.getAccountService();
        try {
            accountService.addNewUser(new UserProfile(login,password,""));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.getWriter().println("Was added " + login + " " + password);
        resp.setStatus(HttpServletResponse.SC_OK);
        //super.doPost(req, resp);
    }
}
