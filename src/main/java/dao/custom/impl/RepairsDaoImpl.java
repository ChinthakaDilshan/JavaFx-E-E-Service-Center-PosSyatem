package dao.custom.impl;

import dao.custom.RepairsDao;
import dao.util.HibernateUtil;
import dto.OrderDetailDto;
import dto.RepairInfoDto;
import dto.RepairsDto;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class RepairsDaoImpl implements RepairsDao {
    @Override
    public boolean save(RepairsDto dto) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Repairs repairs = new Repairs(
                dto.getOrderId()
        );
        repairs.setItem(session.find(Item.class,dto.getItemCode()));
        session.save(repairs);

        List<RepairInfoDto> list = dto.getList(); //dto type

        for (RepairInfoDto detailDto:list) {
            RepairInfo repairInfo = new RepairInfo(
                    new RepairInfoKey(detailDto.getOrderId(),detailDto.getItemCode(),detailDto.getRepairItemCode()),
                    session.find(RepairItem.class, detailDto.getRepairItemCode()),
                    repairs,

                    detailDto.getAdvance(),
                    detailDto.getTotal(),
                    detailDto.getServiceCharge()

            );
            session.save(repairInfo);
        }

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(RepairsDto entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<RepairsDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
