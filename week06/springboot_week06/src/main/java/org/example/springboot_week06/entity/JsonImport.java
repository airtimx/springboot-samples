package org.example.springboot_week06.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("main_table")
public class JsonImport {

    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String jsonData;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
}
