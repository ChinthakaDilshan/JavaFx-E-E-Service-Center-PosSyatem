package dto.tm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RepairItemTm {
    private String repairItemCode;
    private String repairItemName;
    private double repairItemPrice;
    private int qty;
    private javafx.scene.control.Button btn;



    public RepairItemTm(String repairItemCode, String repairItemName, double repairItemPrice, javafx.scene.control.Button btn) {
        this.repairItemCode = repairItemCode;
        this.repairItemName = repairItemName;
        this.repairItemPrice = repairItemPrice;
        this.qty = qty;
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "RepairItemTm{" +
                "repairItemCode='" + repairItemCode + '\'' +
                ", repairItemName='" + repairItemName + '\'' +
                ", repairItemPrice='" + repairItemPrice + '\'' +
                ", qty='" + qty + '\'' +
                ", btn=" + btn +
                '}';
    }
}
