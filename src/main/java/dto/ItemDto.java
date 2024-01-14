package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ItemDto {
    private String itemCode;
    private String itemName;
    private String category;

    public ItemDto(String itemCode,String itemName,String category){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
    }

}
