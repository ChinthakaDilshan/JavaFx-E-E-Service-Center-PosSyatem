package bo;

import bo.custom.impl.*;
import dao.util.BoType;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case USERS: return (T) new UsersBoImpl();
            case ITEM: return (T) new ItemBoImpl();
            case REPAIRITEM: return (T) new RepairItemBoImpl();
            case CUSTOMER: return (T) new CustomerBoImpl();
            case ORDERDETAIL: return (T) new OrderDetailBoImpl();
            case ORDER: return (T) new OrderBoImpl();
            case REPAIRS: return (T) new RepairsBoImpl();
        }
        return null;
    }
}
