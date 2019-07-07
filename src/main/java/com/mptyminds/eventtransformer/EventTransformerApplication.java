package com.mptyminds.eventtransformer;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Log4j2
@SpringBootApplication
public class EventTransformerApplication {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(EventTransformerApplication.class, args);
    }

    public Properties getProperties() {
        Properties props = new Properties();
        CompositePropertySource bootstrapProperties =
                (CompositePropertySource) ((AbstractEnvironment) environment).getPropertySources().get("bootstrapProperties");
        for (String propertyName : bootstrapProperties.getPropertyNames()) {
            props.put(propertyName, bootstrapProperties.getProperty(propertyName));
        }

        log.info(props);
        return props;
    }

}
