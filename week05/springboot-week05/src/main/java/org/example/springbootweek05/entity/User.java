package org.example.springbootweek05.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户表
 * @TableName t_user
 */
@Data
public class User {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;




}