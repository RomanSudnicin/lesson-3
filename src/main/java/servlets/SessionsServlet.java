package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by roman on 06.08.16.
 */
public class SessionsServlet extends HttpServlet {
    //private final Acco

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    // Sing In
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if(login == null || pass == null){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        //if()
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
