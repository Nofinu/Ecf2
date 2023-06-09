package org.example.Service;

import org.example.model.Course;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class CourseService extends BaseService<Course> {

    @Override
    public boolean create(Course element) {
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(element);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean update(Course element) {
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(element);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(Course element) {
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.remove(element);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Course findById(int id) {
        Course course = null;
        session =sessionFactory.openSession();
        course = session.get(Course.class,id);
        session.close();
        return course;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = null;
        session = sessionFactory.openSession();
        courses = session.createQuery("from Course ", Course.class).list();
        session.close();
        return courses;
    }

    public List<Course> findCoursebyDate (){
        List<Course> courses =null;
        session = sessionFactory.openSession();
        Date today = new Date(System.currentTimeMillis());
        Query<Course> courseQuery = session.createQuery("from Course where date >= :date");
        courseQuery.setParameter("date",today);
        courses = courseQuery.list();
        session.close();
        return courses;
    }
}
