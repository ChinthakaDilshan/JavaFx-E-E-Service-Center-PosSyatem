package dto;

public class UsersDto {
    private String email;
    private String password;
    private String jobRole;

    public UsersDto(String email,String password,String jobRole){
        this.email = email;
        this.password = password;
        this.jobRole = jobRole;
    }

    public UsersDto(){}

    public String toString(){
        return "Users{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", jobRole='" + jobRole +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }



}
