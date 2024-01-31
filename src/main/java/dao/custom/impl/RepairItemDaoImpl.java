package dao.custom.impl;

import dao.custom.RepairItemDao;
import dao.util.HibernateUtil;
import dto.RepairItemDto;
import entity.RepairItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class RepairItemDaoImpl implements RepairItemDao {
    @Override
    public boolean save(RepairItem entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(RepairItem entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();
        RepairItem item = session.find(RepairItem.class, entity.getRepairItemCode());
        item.setRepairItemName(entity.getRepairItemName());
        item.setRepairItemPrice(Double.parseDouble(String.valueOf(entity.getRepairItemPrice())));
        session.save(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(RepairItem.class,value));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<RepairItem> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM RepairItem");
        List<RepairItem> list = query.list();
        session.close();
        return list;
    }

    @Override
    public RepairItemDto searchRepairItem(String repairItemCode) {
        return null;
    }
}
