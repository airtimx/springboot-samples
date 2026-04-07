package org.example.springbootweek04dome.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


//    // 支持多种日期格式
//    private static final List<DateTimeFormatter> LOCAL_DATE_FORMATTERS = Arrays.asList(
//            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
//            DateTimeFormatter.ofPattern("yyyy-M-d"),
//            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
//            DateTimeFormatter.ofPattern("yyyy/M/d")
//    );

//    @Bean
//    public MappingJackson2HttpMessageConverter customJacksonConverter() {
////定义日期格式化规则
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
////构建 ObjectMapper,配置序列化规则
//        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
//                .modules(
//                        new JavaTimeModule()
//                                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
//                                .addDeserializer(LocalDate.class, new FlexibleLocalDateDeserializer()),
//                        new SimpleModule().addSerializer(Long.class, ToStringSerializer.instance)
//                )
//                .build();
//        return new MappingJackson2HttpMessageConverter(objectMapper);
//    }
//
//    // 自定义灵活的LocalDate反序列化器
//    private static class FlexibleLocalDateDeserializer extends StdDeserializer<LocalDate> {
//        public FlexibleLocalDateDeserializer() {
//            super(LocalDate.class);
//        }
//
//        @Override
//        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
//            String dateStr = p.getValueAsString().trim();
//            for (DateTimeFormatter formatter : LOCAL_DATE_FORMATTERS) {
//                try {
//                    return LocalDate.parse(dateStr, formatter);
//                } catch (DateTimeParseException e) {
//                    // 继续尝试下一个格式
//                }
//            }
//            throw ctxt.weirdStringException(dateStr, LocalDate.class,
//                    "无法解析日期字符串，支持的格式有: yyyy-MM-dd, yyyy-M-d, yyyy/MM/dd, yyyy/M/d");
//        }
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 添加静态资源处理器，用于访问上传的文件
        // 使用 file:./upload/ 相对于项目运行目录
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:./upload/");
    }
}