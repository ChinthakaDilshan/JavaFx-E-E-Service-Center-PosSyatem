package dto;

import lombok.*;


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

    public RepairInfoDto(String orderId, String itemCode, String repairItemCode, int qty, double unitPrice, double serviceCharge, double advance, double total) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.repairItemCode = repairItemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.serviceCharge = serviceCharge;
        this.advance = advance;
        this.total = total;
    }




}
