package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry serviceRegistry;

    public static  SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
                MetadataSources sources = new MetadataSources(serviceRegistry);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();

                if(serviceRegistry!=null){
                    StandardServiceRegistryBuilder.destroy(serviceRegistry);
                }
            }

        }

        return sessionFactory;
    }

    public static void shutdown(){
        if(serviceRegistry!=null){
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }



//    help.about *

//    Create a helper class to bootstrap hibernate SessionFactory.
//    In most Hibernate applications, the SessionFactory should be instantiated once during application initialization.
//    The single instance should then be used by all code in a particular process,
//    and any Session should be created using this single SessionFactory.

}
