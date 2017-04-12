package sensesnet.coffeeShop.daoUtils;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Hibernate work with session. At first hibernate will open session, after that
 * hibernate create transaction . In transaction  you can do CRUD with DB.
 * After "commit" and session will be "close".
 */

public class HibernateUtil {

    private static HibernateUtil util = null;
    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory = null;
    private final ThreadLocal sessions = new ThreadLocal();

    private HibernateUtil() {    }

        static {
            // Create the SessionFactory from hibernate.cfg.xml
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

    public Session getSession() {
        Session session = (Session) sessions.get();
        if (session == null) {
            session = sessionFactory.openSession();
            sessions.set(session);
        }

        return session;
    }

    /**
     * method to get HibernateUtil with opened SessionFactory
     *
     * @return
     */
    public static synchronized HibernateUtil getHibernateUtil() {
        if (util == null) {
            util = new HibernateUtil();
        }
        return util;
    }

    /**
     * close session and clear ThreadLocal variable
     *
     * @param session
     */
    public void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
            sessions.remove();
        }
    }

}
