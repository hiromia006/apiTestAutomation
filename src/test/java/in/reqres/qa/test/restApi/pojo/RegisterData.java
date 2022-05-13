package in.reqres.qa.test.restApi.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterData {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public RegisterData(String email, String password) {
        this.email = email;
        this.password = password;
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
}
