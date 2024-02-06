package dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RepairInfoDto {
    private String orderId;
    private String itemCode;
    private String repairItemCode;
    private int qty;
    private double unitPrice;
    private double serviceCharge;
    private double advance;
    private double total;

}
