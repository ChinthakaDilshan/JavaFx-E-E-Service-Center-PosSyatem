package dao.custom;

import dao.CrudDao;
import dto.UsersDto;
import entity.Users;

public interface UsersDao extends CrudDao<Users> {
    UsersDto searchCustomer(String email);

    UsersDto searchUsers(String email);
}
