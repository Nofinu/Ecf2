package org.example.Service;

import com.mysql.cj.xdevapi.AddResult;
import org.example.model.Address;
import org.example.model.Customer;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerService extends BaseService<Customer>{

    @Override
    public boolean create(Customer element) {
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

    public boolean createAddress(Address element) {
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
    public boolean update(Customer element) {
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
    public boolean delete(Customer element) {
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
    public Customer findById(int id) {
        Customer customer = null;
        session =sessionFactory.openSession();
        customer = session.get(Customer.class,id);
        session.close();
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = null;
        session = sessionFactory.openSession();
        customers = session.createQuery("from Customer ", Customer.class).list();
        session.close();
        return customers;
    }

    public Address findAddress (String street, String city, String postalCode){
        Address address = null;
        session = sessionFactory.openSession();
        Query<Address> addressQuery = session.createQuery("from Address as a where a.street = :street and a.city = :city and a.postalCode = :postalCode", Address.class);
        addressQuery.setParameter("street",street);
        addressQuery.setParameter("city",city);
        addressQuery.setParameter("postalCode",postalCode);
        address =  addressQuery.getSingleResult();
        session.close();
        return address;
    }

    public void end() {
        sessionFactory.close();
    }

}
