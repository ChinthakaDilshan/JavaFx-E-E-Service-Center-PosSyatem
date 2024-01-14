package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class CustomerDto {
    private String customerCode;
    private String customerName;
    private int customerContact;
    private String customerEmail;

    public CustomerDto(String customerCode,String customerName,int customerContact,String customerEmail){
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.customerEmail  = customerEmail;
    }
}
