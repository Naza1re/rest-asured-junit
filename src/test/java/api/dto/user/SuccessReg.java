package api.dto.user;

public class SuccessReg {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private int id;

    public SuccessReg(int id, String token) {
        this.id = id;
        this.token = token;
    }
    public SuccessReg() {

    }

    private String token;
}
