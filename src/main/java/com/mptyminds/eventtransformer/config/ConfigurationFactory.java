package com.mptyminds.eventtransformer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
@RefreshScope
public class ConfigurationFactory {

    @Autowired
    private Environment environment;

    @Value("${event.sources}")
    private String configSources;

    private Properties allConfigurationProperties;

    @PostConstruct
    public Properties properties() {
        if(null == allConfigurationProperties) {
            allConfigurationProperties = new Properties();
        }
        CompositePropertySource bootstrapProperties = (CompositePropertySource)  ((AbstractEnvironment) environment).getPropertySources().get("bootstrapProperties");
        if(null != bootstrapProperties) {
            for (String propertyName : bootstrapProperties.getPropertyNames()) {
                allConfigurationProperties.put(propertyName, bootstrapProperties.getProperty(propertyName));
            }
        }

        return allConfigurationProperties;
    }
}
