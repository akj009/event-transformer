package com.mptyminds.eventtransformer.config;

import com.jsoniter.JsonIterator;
import com.mptyminds.eventtransformer.models.EventSchema;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

import static com.mptyminds.eventtransformer.config.ConfigurationConstants.SOURCE_EVENT_PATHS_SUFFIX;
import static com.mptyminds.eventtransformer.config.ConfigurationConstants.SOURCE_EVENT_SCHEMA_SUFFIX;

@Log4j2
@Component
@RefreshScope
@Getter
public class ConfigurationFactory {

    @Autowired
    private Environment environment;

    @Value("${event.sources.key}")
    private String eventSourcesKey;

    private Properties allConfigurationProperties;
    public static List<String> eventSources; // contains only name
    public static Map<String, String> eventSourceNameMap; // contains source: csv of event names
    public static Map<String, String> eventSourceNamePathMap;
    public static Map<String, EventSchema> eventSourceNameSchemaMap;

    @EventListener(classes = {ContextRefreshedEvent.class, RefreshScopeRefreshedEvent.class})
    public synchronized void prepareConfigurations() {
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
        eventSources = prepareSourceList(allConfigurationProperties);
        eventSourceNameMap = prepareSourceNameMap(allConfigurationProperties, eventSources);
        eventSourceNamePathMap = prepareSourcePathMap(allConfigurationProperties);
        eventSourceNameSchemaMap = prepareSourcewiseSchemaMap(allConfigurationProperties);

    }

    private Map<String, EventSchema> prepareSourcewiseSchemaMap(Properties configurationProperties) {
        eventSourceNameSchemaMap = new HashMap<>();
        configurationProperties.forEach((key, value) -> {
            if (((String) key).endsWith(SOURCE_EVENT_SCHEMA_SUFFIX)) {
                final String eventSchemaJsonString = (String) value;
                final String eventSchemaKey = (String) key;
                eventSourceNameSchemaMap.put(eventSchemaKey, new EventSchema.EventSchemaModelBuilder()
                        .setEventName(eventSchemaKey)
                        .setRawEventSchemaString(eventSchemaJsonString)
                        .build());
            }
        });
        return eventSourceNameSchemaMap;
    }

    private Map<String, String> prepareSourcePathMap(Properties configurationProperties) {
        eventSourceNamePathMap = new HashMap<>();
        configurationProperties.forEach((key, value) -> {
            if (((String) key).endsWith(SOURCE_EVENT_PATHS_SUFFIX)) {
                String eventNamePath = (String) value;
                if(eventNamePath.startsWith("$(")) {
                    eventNamePath = eventNamePath.replaceAll("[\\$\\(\\)]", "");
                }
                eventSourceNamePathMap.put(((String) key), eventNamePath);
            }
        });
        return eventSourceNamePathMap;
    }

    private void prepareMapFromConfig(Properties configurationProperties, String lookupSuffix, Map<String, String> targetMap) {
        configurationProperties.forEach((key, value) -> {
            if (((String) key).endsWith(lookupSuffix)) {
                targetMap.put(((String) key), ((String) value));
            }
        });
        log.info(":::: {} map prepared: {}", lookupSuffix, targetMap);
    }

    private Map<String, String> prepareSourceNameMap(Properties configurationProperties, List<String> eventSources) {
        eventSourceNameMap = new HashMap<>();
        eventSources.forEach(sourceKey -> {
            final String sourceEventsStr = configurationProperties.getProperty(sourceKey + ConfigurationConstants.SOURCE_EVENT_NAMES_SUFFIX);
            if(StringUtils.hasText(sourceEventsStr)) {
                eventSourceNameMap.put(sourceKey, sourceEventsStr);
            }
        });
        return eventSourceNameMap;
    }

    private List<String> prepareSourceList(Properties configurationProperties) {
        final String eventSourcesJsonStr = configurationProperties.getProperty(eventSourcesKey);
        if(StringUtils.hasText(eventSourcesJsonStr)) {
            final Map<String, Object> eventSourcesMap = JsonIterator.deserialize(eventSourcesJsonStr).as(Map.class);
            return new LinkedList<>(eventSourcesMap.keySet());
        }
        return new LinkedList<>();
    }
}
