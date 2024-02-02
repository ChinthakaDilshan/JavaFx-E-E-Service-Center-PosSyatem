package bo.custom.impl;

import bo.custom.RepairsBo;
import dao.custom.OrderDao;
import dao.custom.RepairsDao;
import dao.custom.impl.OrderDaoImpl;
import dao.custom.impl.RepairsDaoImpl;
import dto.RepairsDto;

import java.sql.SQLException;
import java.util.List;

public class RepairsBoImpl implements RepairsBo {
    private RepairsDao repairsDao = new RepairsDaoImpl();
    @Override
    public boolean saveRepairs(RepairsDto dto) throws SQLException, ClassNotFoundException {
        return repairsDao.save(dto);
    }

    @Override
    public List<RepairsDto> allRepairs() throws SQLException, ClassNotFoundException {
        return null;
    }
}
