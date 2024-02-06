package dao.custom;

import dao.CrudDao;
import dto.UsersDto;
import entity.Users;

import java.sql.SQLException;

public interface UsersDao extends CrudDao<Users> {


    UsersDto searchUsers(String email);


}
