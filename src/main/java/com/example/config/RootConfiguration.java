package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans(
        @ComponentScan("com.example.service")
)
public class RootConfiguration {
}
