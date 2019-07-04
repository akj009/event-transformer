package com.mptyminds.eventtransformer.service;

import com.jsoniter.JsonIterator;
import com.jsoniter.ValueType;
import com.jsoniter.any.Any;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Log4j2
@Component
public class EventTransformer {

    private String schemaStr = String.join("", Files.readAllLines(Paths.get("src/main/resources/testschema.json")));
    private Map<String, Object> traversedPathsCache = new HashMap<>();
//    private Map<String, > transformationSchema;

    public EventTransformer() throws IOException {
        log.info("input schema string: {}", schemaStr);
        final LinkedHashMap deserializedSchema = JsonIterator.deserialize(schemaStr).as(LinkedHashMap.class);
        prepareMissingNodeReferenceMap(deserializedSchema);
    }

    private void prepareMissingNodeReferenceMap(LinkedHashMap transformationSchema) {
        for(Map.Entry schemaNode : transformationSchema.entrySet()) {
            log.info("{}: {}", key, value);
            if(value instanceof String) {
                log.info("checking for path");

            } else if(value instanceof Map) {
                prepareMissingNodeReferenceMap(value);
            }
        }

    }

    public String transformEvent(String inputJson) {
        final Any deserializeInput = JsonIterator.deserialize(inputJson);
        return null;
    }

    public static void main(String[] args) throws IOException {
        EventTransformer eventTransformer = new EventTransformer();
    }
}
