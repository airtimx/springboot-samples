package jiangyuze.example.hollespringsamples.VO;

public class HealthVO {
    private String projectName;
    private String version;
    private String serverTime;
    private String status;

    public HealthVO() {
    }

    public HealthVO(String projectName, String version, String serverTime, String status) {
        this.projectName = projectName;
        this.version = version;
        this.serverTime = serverTime;
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
