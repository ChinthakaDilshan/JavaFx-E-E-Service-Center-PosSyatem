package dao.custom.impl;

import dao.custom.OrderDetailsDao;
import dao.custom.RepairInfoDao;
import dao.util.HibernateUtil;
import dto.OrderDetailDto;
import dto.OrderDto;
import dto.RepairInfoDto;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class RepairInfoDaoImpl implements RepairInfoDao {
    private RepairInfoDao repairInfoDao = new RepairInfoDaoImpl();

    @Override
    public boolean save(RepairInfoDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(RepairInfoDto entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<RepairInfoDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}