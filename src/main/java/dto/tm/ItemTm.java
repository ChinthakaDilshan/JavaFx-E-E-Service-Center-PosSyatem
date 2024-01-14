package dto.tm;

import javafx.scene.control.Button;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemTm {
    private String itemCode;
    private String itemName;
    private String category;
    private Button btn;
    public ItemTm(String itemCode, String itemName, String category, Button btn){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "ItemTm{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", btn=" + btn +
                '}';
    }
}
