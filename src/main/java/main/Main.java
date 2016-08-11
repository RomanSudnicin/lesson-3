package main;

import accounts.AccountService;
import dbService.DBService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.StopServlet;

/**
 * Created by roman on 06.08.16.
 */
public class Main {
    public static void main (String[] args) throws Exception {

        //DBService.getDBService().printConnectInfo();

        //System.exit(0);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(new SignUpServlet()),"/signup");
        servletContextHandler.addServlet(new ServletHolder(new SignInServlet()),"/signin");
        servletContextHandler.addServlet(new ServletHolder(new StopServlet()),"/stop");


        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");


        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {resourceHandler, servletContextHandler });

        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
        System.out.println("Server started");
        server.join();

    }
}
