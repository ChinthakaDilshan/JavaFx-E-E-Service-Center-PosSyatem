package bo.custom;

import bo.SuperBo;
import dto.ItemDto;
import dto.RepairItemDto;

import java.sql.SQLException;
import java.util.List;

public interface RepairItemBo extends SuperBo {
    boolean saveRepairItem(RepairItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateRepairItem(RepairItemDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteRepairItem(String itemCode) throws SQLException, ClassNotFoundException;
    List<RepairItemDto> allRepairItems() throws SQLException, ClassNotFoundException;
}
