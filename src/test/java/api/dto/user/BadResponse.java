package api.dto.user;

public class BadResponse {
    public BadResponse(String error) {
        this.error = error;
    }

    private String error;

    public BadResponse() {

    }

    public void serError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
