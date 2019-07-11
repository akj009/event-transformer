package com.mptyminds.eventtransformer.service;

import com.jsoniter.any.Any;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    public Any getEventSchema(String eventName, String sourceId) {
        return null;
    }
}
