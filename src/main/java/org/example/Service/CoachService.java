package org.example.Service;

import org.example.model.Coach;

import java.util.List;

public class CoachService extends BaseService<Coach> {
    @Override
    public boolean create(Coach element) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(element);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Coach element) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(element);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Coach element) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.remove(element);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Coach findById(int id) {
        Coach coach = null;
        session = sessionFactory.openSession();
        coach = session.get(Coach.class, id);
        session.close();
        return coach;
    }

    @Override
    public List<Coach> findAll() {
        List<Coach> coachs = null;
        session = sessionFactory.openSession();
        coachs = session.createQuery("from Coach ", Coach.class).list();
        session.close();
        return coachs;
    }
}
