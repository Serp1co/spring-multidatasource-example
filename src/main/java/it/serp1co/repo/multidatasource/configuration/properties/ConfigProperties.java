package it.serp1co.repo.multidatasource.configuration.properties;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Component
@ConfigurationProperties("spring.conf")
public class ConfigProperties {

    //Put confs here

}
