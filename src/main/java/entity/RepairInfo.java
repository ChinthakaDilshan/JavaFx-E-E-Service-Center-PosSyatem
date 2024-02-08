package entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class RepairInfo {
    @EmbeddedId
    private RepairInfoKey id;


    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private String orderId;

    @ManyToOne
    @MapsId("itemCode")
    @JoinColumn(name = "item_code")
    private Item item;


    @ManyToOne
    @MapsId("repairItemCode")
    @JoinColumn(name = "repairItem_code")
    RepairItem repairItem;


    private int qty;
    private double unitPrice;
    private double advance;
    private double total;
    private double serviceCharge;


    public <T> RepairInfo(RepairInfoKey repairInfoKey, T t, Repairs repairs, int qty, double unitPrice, double advance, double total, double serviceCharge) {
        this.id = id;
        this.orderId = orderId;
        this.item = item;
        this.repairItem = repairItem;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.advance = advance;
        this.total = total;
        this.serviceCharge = serviceCharge;
    }
}




