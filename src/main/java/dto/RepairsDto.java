package dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RepairsDto {
    private String orderId;

    private String itemCode;
    private List<RepairInfoDto> list;

}
