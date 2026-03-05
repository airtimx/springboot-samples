package jiangyuze.example.hollespringsamples;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public ResultVO<String> hello() {
        return ResultVO.success("Hello Spring Boot");
    }
}
