package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class RepairItemDto {
    private String repairItemCode;
    private String repairItemName;
    private double repairItemPrice;

    public RepairItemDto(String repairItemCode,String repairItemName,double repairItemPrice){
        this.repairItemCode = repairItemCode;
        this.repairItemName = repairItemName;
        this.repairItemPrice = repairItemPrice;

    }
}
