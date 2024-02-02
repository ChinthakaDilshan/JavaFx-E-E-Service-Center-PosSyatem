package bo.custom;

import bo.SuperBo;
import dto.OrderDto;
import dto.RepairsDto;

import java.sql.SQLException;
import java.util.List;

public interface RepairsBo extends SuperBo {
    boolean saveRepairs(RepairsDto dto)throws SQLException, ClassNotFoundException;


    List<RepairsDto> allRepairs() throws SQLException, ClassNotFoundException;
}
