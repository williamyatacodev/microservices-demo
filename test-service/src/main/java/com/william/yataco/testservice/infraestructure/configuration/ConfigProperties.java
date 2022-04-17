package com.william.yataco.testservice.infraestructure.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application")
public class ConfigProperties {

    String urlLogin;
    String urlMe;
    String urlMovement;
    String listMax;
}
