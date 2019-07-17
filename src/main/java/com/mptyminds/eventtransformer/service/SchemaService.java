package com.mptyminds.eventtransformer.service;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.mptyminds.eventtransformer.config.ConfigurationFactory;
import com.mptyminds.eventtransformer.models.EventSchema;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.mptyminds.eventtransformer.config.ConfigurationConstants.SOURCE_EVENT_SCHEMA_SUFFIX;

@Service
@RefreshScope
public class SchemaService {

    private void prepareMissingNodeReferenceMap(Map transformationSchema, String rootPath) {
        /*transformationSchema.forEach((key, value) -> {
            log.info("{}: {}", key, value);
            final String schemaPathKey = rootPath + (DOT) + (key);
            if (value instanceof String) {
                log.info("checking for path");
                Matcher matcher = PATH_PATTERN.matcher((String) value);
                if(matcher.matches()) {
                    String path = matcher.group(1);
                    if(schemaPathKey.startsWith(".")) {
                        lookupPathsMap.put(schemaPathKey.substring(1), path);
                    } else {
                        lookupPathsMap.put(schemaPathKey, path);
                    }
                }
            } else if (value instanceof Map) {
                prepareMissingNodeReferenceMap((Map) value, schemaPathKey);
                return;
            }
        });
        log.info(lookupPathsMap);*/
    }

    public EventSchema getEventSchema(String eventName, String sourceId) {
        return ConfigurationFactory.eventSourceNameSchemaMap.get(sourceId + "_" + eventName + "_event" + SOURCE_EVENT_SCHEMA_SUFFIX);
    }
}
