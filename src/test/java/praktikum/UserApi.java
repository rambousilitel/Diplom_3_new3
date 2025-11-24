package praktikum;

public class UserApi {
    private String email;
    private String password;
    private String name;

    public UserApi() {
    }

    public UserApi(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
