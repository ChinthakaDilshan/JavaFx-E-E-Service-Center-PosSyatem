package bo.custom;

import bo.SuperBo;
import bo.custom.impl.UsersBoImpl;
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

        }
        return null;
    }
}
