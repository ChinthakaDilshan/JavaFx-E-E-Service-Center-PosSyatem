package dao.custom.impl;

import dao.custom.CustomerDao;
import dao.util.HibernateUtil;
import dto.CustomerDto;
import entity.Customer;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();
        Customer customer = session.find(Customer.class, entity.getCustomerCode());
        customer.setCustomerName(entity.getCustomerName());
        customer.setCustomerContact(Integer.parseInt(String.valueOf(entity.getCustomerContact())));
        customer.setCustomerEmail(entity.getCustomerEmail());
        session.save(customer);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Customer.class,value));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Customer");
        List<Customer> list = query.list();
        session.close();
        return list;
    }

    @Override
    public CustomerDto searchCustomer(String customerCode) {
        return null;
    }
}
