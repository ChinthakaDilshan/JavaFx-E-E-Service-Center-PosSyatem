package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Users {
    @Id
    private String email;
    private String password;
    private String jobRole;

    public Users(String email, String password, String jobRole) {
        this.email = email;
        this.password = password;
        this.jobRole = jobRole;
    }
}
