package jiangyuze.example.hollespringsamples.controller;

import jiangyuze.example.hollespringsamples.VO.MetricsVO;
import jiangyuze.example.hollespringsamples.VO.ResultVO;
import jiangyuze.example.hollespringsamples.VO.SystemInfoVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class SystemInfoController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private Integer serverPort;

    private final Environment environment;

    public SystemInfoController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/info")
    public ResultVO<SystemInfoVO> getSystemInfo() {
        SystemInfoVO systemInfoVO = new SystemInfoVO(
                appName,
                System.getProperty("java.version"),
                System.getProperty("os.name"),
                System.getProperty("os.version"),
                String.join(",", environment.getActiveProfiles()),
                serverPort
        );
        return ResultVO.success(systemInfoVO);
    }

    @GetMapping("/env")
    public ResultVO<Map<String, String>> getEnvironmentInfo() {
        Map<String, String> envInfo = new HashMap<>();

        // 返回部分安全的环境变量
        envInfo.put("JAVA_HOME", System.getProperty("java.home"));
        envInfo.put("JAVA_VERSION", System.getProperty("java.version"));
        envInfo.put("OS_NAME", System.getProperty("os.name"));
        envInfo.put("OS_ARCH", System.getProperty("os.arch"));
        envInfo.put("USER_NAME", System.getProperty("user.name"));
        envInfo.put("USER_DIR", System.getProperty("user.dir"));
        envInfo.put("TEMP_DIR", System.getProperty("java.io.tmpdir"));
        envInfo.put("APP_NAME", appName);
        envInfo.put("SERVER_PORT", String.valueOf(serverPort));

        // 返回当前激活的环境配置
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length > 0) {
            envInfo.put("ACTIVE_PROFILES", String.join(",", activeProfiles));
        } else {
            envInfo.put("ACTIVE_PROFILES", "default");
        }

        return ResultVO.success(envInfo);
    }

    @GetMapping("/metrics")
    public ResultVO<MetricsVO> getMetrics() {
        Runtime runtime = Runtime.getRuntime();

        // 内存信息
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        MetricsVO.MemoryInfo memoryInfo = new MetricsVO.MemoryInfo(
                maxMemory,
                totalMemory,
                freeMemory,
                usedMemory
        );

        // 线程信息
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        MetricsVO.ThreadInfo threadInfo = new MetricsVO.ThreadInfo(
                threadMXBean.getThreadCount(),
                threadMXBean.getPeakThreadCount()
        );

        // 运行时信息
        MetricsVO.RuntimeInfo runtimeInfo = new MetricsVO.RuntimeInfo(
                System.getProperty("java.version"),
                System.getProperty("java.vendor"),
                ManagementFactory.getRuntimeMXBean().getUptime()
        );

        MetricsVO metricsVO = new MetricsVO(memoryInfo, threadInfo, runtimeInfo);
        return ResultVO.success(metricsVO);
    }
}
