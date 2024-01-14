package dao;


import dao.custom.impl.ItemDaoImpl;
import dao.custom.impl.UsersDaoImpl;
//import dao.custom.impl.ItemDaoImpl;
import dao.util.DaoType;

import static dao.util.BoType.USERS;

public class DaoFactory {
    private static DaoFactory daoFactory = null;

    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return daoFactory!=null? daoFactory:(daoFactory=new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case USERS: return(T) new UsersDaoImpl();
            case ITEM: return (T) new ItemDaoImpl();
        }
        return null;
    }
}
