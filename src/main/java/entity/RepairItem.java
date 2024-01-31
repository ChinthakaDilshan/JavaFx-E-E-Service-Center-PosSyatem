package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class RepairItem {
    @Id
    private String repairItemCode;
    private String repairItemName;
    private double repairItemPrice;

    public RepairItem(String repairItemCode,String repairItemName,double repairItemPrice){
        this.repairItemCode = repairItemCode;
        this.repairItemName = repairItemName;
        this.repairItemPrice = repairItemPrice;

    }
}
