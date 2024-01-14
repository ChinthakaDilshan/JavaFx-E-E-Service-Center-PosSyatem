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
public class CustomerTm {
    private String customerCode;
    private String customerName;
    private int customerContact;
    private String customerEmail;
    private Button btn;

    public CustomerTm(String customerCode,String customerName,int customerContact,String customerEmail,Button btn){
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.customerEmail  = customerEmail;
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CustomerTm{" +
                "customerCode='" + customerCode + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerContact='" + customerContact + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", btn=" + btn +
                '}';
    }
}
