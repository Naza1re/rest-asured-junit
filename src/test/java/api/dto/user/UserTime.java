package api.dto.user;

public class UserTime {
    private String name;
    private String job;

    public UserTime(String job, String name) {
        this.job = job;
        this.name = name;
    }
    public UserTime() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
