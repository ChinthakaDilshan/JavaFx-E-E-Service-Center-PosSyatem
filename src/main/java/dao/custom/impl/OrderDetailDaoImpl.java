package dao.custom.impl;

import dao.custom.OrderDetailsDao;
import dao.util.HibernateUtil;
import db.DBConnection;
import dto.OrderDetailDto;
import dao.custom.OrderDetailsDao;
import entity.Item;
import entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailsDao {
//    @Override
//    public boolean saveOrderDetails(List<OrderDetailDto> list) throws SQLException, ClassNotFoundException {
//        String sql = "INSERT INTO orderdetail VALUES(?,?,?)";
//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//
//        for (OrderDetailDto dto:list) {
//            pstm.setString(1,dto.getOrderId());
//            pstm.setString(2,dto.getItemCode());
//            pstm.setDouble(4,dto.getAdvancePrice());
//            if (!(pstm.executeUpdate()>0)){
//                return false;
//            }
//        }
//        return true;
//    }

    @Override
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM OrderDetail ");
        List<OrderDetail> list = query.list();
        session.close();
        return list;
    }
}