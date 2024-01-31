package dao.custom;

import dao.CrudDao;
import dto.ItemDto;
import dto.RepairItemDto;
import entity.RepairItem;

public interface RepairItemDao extends CrudDao<RepairItem> {
    RepairItemDto searchRepairItem(String repairItemCode);
}
