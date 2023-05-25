package org.example.Service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public abstract class BaseService<T> {

    protected StandardServiceRegistry registry;

    protected SessionFactory sessionFactory;

    protected Session session;

    public BaseService() {
        registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public abstract void  end();

    public abstract boolean create(T element);

    public abstract boolean update(T element);

    public abstract boolean delete(T element);

    public abstract T findById(int id);

    public abstract List<T> findAll ();
}

