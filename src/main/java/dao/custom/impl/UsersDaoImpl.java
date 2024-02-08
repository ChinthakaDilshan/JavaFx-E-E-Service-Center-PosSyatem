package dao.custom.impl;

import dao.custom.UsersDao;
import dao.util.CrudUtil;
import dao.util.HibernateUtil;
import db.DBConnection;
import dto.UsersDto;
import entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public UsersDto searchUsers(String email) {
        return null;
    }


    public String getEmail(String userName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT email FROM users WHERE username = ?",userName);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return "cd9141404@gmail.com";
    }
    public boolean updatePassword(String userName, String pswrd) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE user SET password = ? WHERE username = ?",pswrd,userName);
    }

    public Boolean update(String userName, String text, String jobRole) {
        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();
        Users users = session.find(Users.class,userName);
        users.setPassword(text);
        users.setJobRole(jobRole);
        session.save(users);
        transaction.commit();
        session.close();
        return true;
    }
}
