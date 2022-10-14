package entities;

public class WebUser {
    private  String email;
    private String password;

    public WebUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
