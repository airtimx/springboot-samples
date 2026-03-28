package org.example.springbootweek04dome.entuty;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class User {
    private String username;
    private Long id;
    private LocalDateTime createTime;
}
