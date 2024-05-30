package api.dto.user;

public class UserTimeResponse extends UserTime {
    private String updatedAt;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public UserTimeResponse() {

    }

    public void setUpdatedAt(String updated) {
        this.updatedAt = updated;
    }

    public UserTimeResponse(String name, String job, String updated) {
        super(name,job);
        this.updatedAt = updated;
    }
}
