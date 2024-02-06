package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RepairsTm extends RecursiveTreeObject<RepairsTm> {
    private String repairItemCode;
    private String repairItemName;
    private double repairItemPrice;
    private int qty;
    private double amount;
    private Button btn;
}
