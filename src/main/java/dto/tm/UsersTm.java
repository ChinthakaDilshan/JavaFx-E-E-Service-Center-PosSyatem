package dto.tm;

public class UsersTm {
    private String email;
    private  String password;
    private String jobRole;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getPassword() {
        return password;
    }

    public String getJobRole() {
        return jobRole;
    }

    public UsersTm(String email, String password, String jobRole){
        this.email = email;
        this.password = password;
        this.jobRole = jobRole;
    }

    @Override
    public String toString() {
        return "UsersTm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", jobRole='" + jobRole +
                '}';
    }

}
