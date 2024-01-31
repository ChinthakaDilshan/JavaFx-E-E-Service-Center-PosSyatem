package bo.custom.impl;


import bo.custom.RepairItemBo;
import dao.DaoFactory;
import dao.custom.RepairItemDao;
import dao.util.DaoType;
import dto.ItemDto;
import dto.RepairItemDto;
import entity.Item;
import entity.RepairItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairItemBoImpl implements RepairItemBo {
    private RepairItemDao repairItemDao = DaoFactory.getInstance().getDao(DaoType.REPAIRITEM);

    @Override
    public boolean saveRepairItem(RepairItemDto dto) throws SQLException, ClassNotFoundException {
        return repairItemDao.save(new RepairItem(
                dto.getRepairItemCode(),
                dto.getRepairItemName(),
                dto.getRepairItemPrice()
        ));
    }

    @Override
    public boolean updateRepairItem(RepairItemDto dto) throws SQLException, ClassNotFoundException {
        return repairItemDao.update(new RepairItem(
                dto.getRepairItemCode(),
                dto.getRepairItemName(),
                dto.getRepairItemPrice()
        ));
    }

    @Override
    public boolean deleteRepairItem(String repairItemCode) throws SQLException, ClassNotFoundException {
        return repairItemDao.delete(repairItemCode);
    }

    @Override
    public List<RepairItemDto> allRepairItems() throws SQLException, ClassNotFoundException {
        List<RepairItem> entityList = repairItemDao.getAll();
        List<RepairItemDto> list = new ArrayList<>();
        for (RepairItem item:entityList) {
            list.add(new RepairItemDto(
                    item.getRepairItemCode(),
                    item.getRepairItemName(),
                    item.getRepairItemPrice()
            ));
        }
        return list;
    }
}
