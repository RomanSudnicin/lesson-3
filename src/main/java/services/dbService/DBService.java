package services.dbService;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import services.accounts.UserProfile;

import java.io.Serializable;

/**
 * Created by roman on 10.08.16.
 */
public class DBService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "create";

    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getMysqlConfigyration();
        sessionFactory = createSessionFactory(configuration);
    }

    public Configuration getMysqlConfigyration() {
        Configuration configuration = new Configuration();


        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/new_schema");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public UserProfile getUserProfile(long id){
        Session session = sessionFactory.openSession();
        UserProfile userProfile = (UserProfile) session.get(UserProfile.class,id);
        session.close();
        return userProfile;
    }

    public UserProfile getUserProfileByLogin(String login){
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserProfile.class);

        UserProfile userProfile = (UserProfile) criteria.add(Restrictions.eq("login",login)).uniqueResult();
        session.close();
        return userProfile;
    }

    public long addUserProfile(UserProfile userProfile){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        long id = (Long) session.save(userProfile);
        transaction.commit();
        session.close();
        return id;
    }
}
