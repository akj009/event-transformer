package com.mptyminds.eventtransformer.models;


import com.jsoniter.JsonIterator;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static com.mptyminds.eventtransformer.util.CommonUtil.DOT;
import static com.mptyminds.eventtransformer.util.CommonUtil.PATH_PATTERN;


@Getter
public class EventSchema {

    final private String eventName;
    final private String rawEventSchemaString;
    final private Map eventDeserializedMap;
    final private Map<String, String> lookupPathsMap;

    private EventSchema(String eventName, String rawEventSchemaString, Map eventDeserializedMap, Map lookupPathsMap) {
        this.eventName = eventName;
        this.rawEventSchemaString = rawEventSchemaString;
        this.eventDeserializedMap = eventDeserializedMap;
        this.lookupPathsMap = lookupPathsMap;
    }

    public static class EventSchemaModelBuilder {

        private String eventName;
        private String rawEventSchemaString;
        private Map<String, String> lookupPathsMap = new HashMap<>();

        public EventSchemaModelBuilder() {}

        public EventSchemaModelBuilder setEventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public EventSchemaModelBuilder setRawEventSchemaString(String rawEventSchemaString) {
            this.rawEventSchemaString = rawEventSchemaString;
            return this;
        }

        public EventSchema build() {

            final Map deserializedSchema = JsonIterator.deserialize(this.rawEventSchemaString).as(LinkedHashMap.class);
            prepareMissingNodeReferenceMap(deserializedSchema, "");
            return new EventSchema(this.eventName, this.rawEventSchemaString, deserializedSchema, lookupPathsMap);

        }

        private void prepareMissingNodeReferenceMap(Map transformationSchema, String rootPath) {
            transformationSchema.forEach((key, value) -> {
                final String schemaPathKey = rootPath + (DOT) + (key);
                if (value instanceof String) {
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
        }
    }
}
