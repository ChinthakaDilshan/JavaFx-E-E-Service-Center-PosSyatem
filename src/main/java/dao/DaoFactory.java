package dao;


import dao.custom.impl.*;

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
            case REPAIRITEM: return (T) new RepairItemDaoImpl();
            case CUSTOMER: return (T) new CustomerDaoImpl();
            case ORDERDETAIL: return (T) new OrderDetailDaoImpl();
            case ORDER: return (T) new OrderDaoImpl();
            case REPAIRINFO : return (T) new RepairInfoDaoImpl();
            case REPAIRS : return (T) new RepairsDaoImpl();
        }
        return null;
    }
}
