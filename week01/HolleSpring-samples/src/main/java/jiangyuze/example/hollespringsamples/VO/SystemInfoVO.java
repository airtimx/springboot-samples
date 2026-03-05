package jiangyuze.example.hollespringsamples.VO;

import java.util.Map;

public class SystemInfoVO {
    private String appName;
    private String javaVersion;
    private String osName;
    private String osVersion;
    private String profiles;
    private Integer serverPort;

    public SystemInfoVO() {
    }

    public SystemInfoVO(String appName, String javaVersion, String osName, String osVersion, String profiles, Integer serverPort) {
        this.appName = appName;
        this.javaVersion = javaVersion;
        this.osName = osName;
        this.osVersion = osVersion;
        this.profiles = profiles;
        this.serverPort = serverPort;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles = profiles;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }
}
