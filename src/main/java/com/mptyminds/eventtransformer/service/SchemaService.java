package com.mptyminds.eventtransformer.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;

import static com.mptyminds.eventtransformer.CommonUtil.DOT;
import static com.mptyminds.eventtransformer.CommonUtil.PATH_PATTERN;

@Service
public class SchemaService {

    private void prepareMissingNodeReferenceMap(Map transformationSchema, String rootPath) {
        transformationSchema.forEach((key, value) -> {
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
        log.info(lookupPathsMap);
    }
}
