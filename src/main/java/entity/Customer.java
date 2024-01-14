package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Customer {
    @Id
    private String customerCode;
    private String customerName;
    private int customerContact;
    private String customerEmail;

    public Customer(String customerCode,String customerName,int customerContact,String customerEmail){
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.customerEmail  = customerEmail;
    }

}
