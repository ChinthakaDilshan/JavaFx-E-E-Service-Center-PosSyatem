package bo.custom.impl;

import bo.custom.UsersBo;
import dao.DaoFactory;
import dao.custom.UsersDao;
import dao.util.DaoType;
import db.DBConnection;
import dto.UsersDto;
import entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersBoImpl implements UsersBo {
    private UsersDao usersDao = DaoFactory.getInstance().getDao(DaoType.USERS);
    @Override
    public boolean saveUsers(UsersDto dto) throws SQLException, ClassNotFoundException {
        return usersDao.save(new Users(
                dto.getEmail(),
                dto.getPassword(),
                dto.getJobRole()
        ));
    }


    @Override
    public boolean updateUsers(UsersDto dto) throws SQLException, ClassNotFoundException {
        return usersDao.update(new Users(
                dto.getEmail(),
                dto.getPassword(),
                dto.getJobRole()
        ));
    }

    @Override
    public boolean deleteUsers(String email) throws SQLException, ClassNotFoundException {
        return usersDao.delete(email);
    }

    @Override
    public List<UsersDto> allUsers() throws SQLException, ClassNotFoundException {
        List<Users> entityList = usersDao.getAll();
        List<UsersDto> list = new ArrayList<>();
        for (Users users:entityList) {
            list.add(new UsersDto(
                    users.getEmail(),
                    users.getPassword(),
                    users.getJobRole()
            ));
        }
        return list;
    }


}
