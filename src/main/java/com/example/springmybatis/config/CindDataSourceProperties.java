package com.example.springmybatis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "cind.datasource")
public class CindDataSourceProperties {
    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;
}
