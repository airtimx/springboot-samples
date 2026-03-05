package jiangyuze.example.hollespringsamples.controller;

import jiangyuze.example.hollespringsamples.VO.HealthVO;
import jiangyuze.example.hollespringsamples.VO.ResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class HealthController {

    @Value("${spring.application.name}")
    private String projectName;

    @Value("${project.version:1.0.0}")
    private String version;

    @GetMapping("/health")
    public ResultVO<HealthVO> health() {
        LocalDateTime now = LocalDateTime.now();
        String serverTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        HealthVO healthVO = new HealthVO(
                projectName,
                version,
                serverTime,
                "UP"
        );

        return ResultVO.success(healthVO);
    }
}
