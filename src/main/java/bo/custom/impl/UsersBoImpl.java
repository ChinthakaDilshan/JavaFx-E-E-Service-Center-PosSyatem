package bo.custom.impl;

import bo.custom.UsersBo;
import dao.DaoFactory;
import dao.custom.UsersDao;
import dao.util.DaoType;
import dto.UsersDto;
import entity.Users;

import java.sql.SQLException;
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
        return null;
    }
}
