package utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utility class to provide a Hibernate SessionFactory.
 * This class is responsible for creating and providing 
 * a SessionFactory instance, which manages database sessions.
 */
public class SessionFactoryProvider {
    
    /**
     * Provides a SessionFactory instance by reading 
     * the Hibernate configuration file.
     *
     * @return SessionFactory - an instance for managing database sessions.
     */
    public static SessionFactory provideSessionFactory() {
        // Create a new Configuration object for Hibernate
        Configuration config = new Configuration();
        
        // Load Hibernate configuration from "hibernate.cfg.xml"
        config.configure("hibernate.cfg.xml"); // No need to specify the full path
        
        // Build and return the SessionFactory object
        return config.buildSessionFactory();
    }
}
