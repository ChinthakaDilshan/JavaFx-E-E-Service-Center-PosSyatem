package dao.custom.impl;

import dao.custom.UsersDao;
import dao.util.HibernateUtil;
import dto.UsersDto;
import entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UsersDaoImpl implements UsersDao {
    @Override
    public boolean save(Users entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(Users entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();
        Users users = session.find(Users.class, entity.getEmail());
        users.setPassword(entity.getPassword());
        users.setJobRole(entity.getJobRole());
        session.save(users);
        transaction.commit();
        session.close();
        return true;
    }




    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Users> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Users");
        List<Users> list = query.list();


        session.close();
        return list;
    }

    @Override
    public UsersDto searchCustomer(String email) {
        return null;
    }

    @Override
    public UsersDto searchUsers(String email) {
        return null;
    }
}
