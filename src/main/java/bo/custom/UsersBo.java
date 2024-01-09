package bo.custom;

import bo.SuperBo;
import dto.UsersDto;

import java.sql.SQLException;
import java.util.List;

public interface UsersBo extends SuperBo {
    boolean saveUsers(UsersDto dto) throws SQLException, ClassNotFoundException;
    boolean updateUsers(UsersDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteUsers(String email) throws SQLException, ClassNotFoundException;
    List<UsersDto> allUsers() throws SQLException, ClassNotFoundException;
}
