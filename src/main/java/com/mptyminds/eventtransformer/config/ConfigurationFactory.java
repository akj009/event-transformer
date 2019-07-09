package com.mptyminds.eventtransformer.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Log4j2
@Component
@RefreshScope
public class ConfigurationFactory {

    @Autowired
    private Environment environment;

    private Properties allConfigurationProperties;

    @EventListener(classes = {ContextRefreshedEvent.class, RefreshScopeRefreshedEvent.class})
    public Properties properties() {
        log.info(":::: context refreshed");
        if(null == allConfigurationProperties) {
            allConfigurationProperties = new Properties();
        }
        CompositePropertySource bootstrapProperties = (CompositePropertySource)  ((AbstractEnvironment) environment).getPropertySources().get("bootstrapProperties");
        if(null != bootstrapProperties) {
            for (String propertyName : bootstrapProperties.getPropertyNames()) {
                allConfigurationProperties.put(propertyName, bootstrapProperties.getProperty(propertyName));
            }
        }

        log.info(":::: loaded configurations: {}", allConfigurationProperties);
        return allConfigurationProperties;
    }
}
