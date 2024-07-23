package com.util;

import com.model.Match;
import com.model.Player;
import org.h2.engine.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static  SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Player.class);
                configuration.addAnnotatedClass(Match.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown(){
        sessionFactory.close();
    }



//    help.about *

//    Create a helper class to bootstrap hibernate SessionFactory.
//    In most Hibernate applications, the SessionFactory should be instantiated once during application initialization.
//    The single instance should then be used by all code in a particular process,
//    and any Session should be created using this single SessionFactory.

}
