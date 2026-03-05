package jiangyuze.example.hollespringsamples.VO;

import java.util.Map;

public class MetricsVO {
    private MemoryInfo memory;
    private ThreadInfo threads;
    private RuntimeInfo runtime;

    public MetricsVO() {
    }

    public MetricsVO(MemoryInfo memory, ThreadInfo threads, RuntimeInfo runtime) {
        this.memory = memory;
        this.threads = threads;
        this.runtime = runtime;
    }

    public MemoryInfo getMemory() {
        return memory;
    }

    public void setMemory(MemoryInfo memory) {
        this.memory = memory;
    }

    public ThreadInfo getThreads() {
        return threads;
    }

    public void setThreads(ThreadInfo threads) {
        this.threads = threads;
    }

    public RuntimeInfo getRuntime() {
        return runtime;
    }

    public void setRuntime(RuntimeInfo runtime) {
        this.runtime = runtime;
    }

    public static class MemoryInfo {
        private long maxMemory;
        private long totalMemory;
        private long freeMemory;
        private long usedMemory;

        public MemoryInfo() {
        }

        public MemoryInfo(long maxMemory, long totalMemory, long freeMemory, long usedMemory) {
            this.maxMemory = maxMemory;
            this.totalMemory = totalMemory;
            this.freeMemory = freeMemory;
            this.usedMemory = usedMemory;
        }

        public long getMaxMemory() {
            return maxMemory;
        }

        public void setMaxMemory(long maxMemory) {
            this.maxMemory = maxMemory;
        }

        public long getTotalMemory() {
            return totalMemory;
        }

        public void setTotalMemory(long totalMemory) {
            this.totalMemory = totalMemory;
        }

        public long getFreeMemory() {
            return freeMemory;
        }

        public void setFreeMemory(long freeMemory) {
            this.freeMemory = freeMemory;
        }

        public long getUsedMemory() {
            return usedMemory;
        }

        public void setUsedMemory(long usedMemory) {
            this.usedMemory = usedMemory;
        }
    }

    public static class ThreadInfo {
        private int threadCount;
        private int peakThreadCount;

        public ThreadInfo() {
        }

        public ThreadInfo(int threadCount, int peakThreadCount) {
            this.threadCount = threadCount;
            this.peakThreadCount = peakThreadCount;
        }

        public int getThreadCount() {
            return threadCount;
        }

        public void setThreadCount(int threadCount) {
            this.threadCount = threadCount;
        }

        public int getPeakThreadCount() {
            return peakThreadCount;
        }

        public void setPeakThreadCount(int peakThreadCount) {
            this.peakThreadCount = peakThreadCount;
        }
    }

    public static class RuntimeInfo {
        private String javaVersion;
        private String javaVendor;
        private long uptime;

        public RuntimeInfo() {
        }

        public RuntimeInfo(String javaVersion, String javaVendor, long uptime) {
            this.javaVersion = javaVersion;
            this.javaVendor = javaVendor;
            this.uptime = uptime;
        }

        public String getJavaVersion() {
            return javaVersion;
        }

        public void setJavaVersion(String javaVersion) {
            this.javaVersion = javaVersion;
        }

        public String getJavaVendor() {
            return javaVendor;
        }

        public void setJavaVendor(String javaVendor) {
            this.javaVendor = javaVendor;
        }

        public long getUptime() {
            return uptime;
        }

        public void setUptime(long uptime) {
            this.uptime = uptime;
        }
    }
}
