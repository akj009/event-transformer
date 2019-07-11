package com.mptyminds.eventtransformer.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.jsoniter.JsonIterator.*;

@Log4j2
@Component
public class EventTransformerService {

    private String schemaStr = String.join("", Files.readAllLines(Paths.get("src/main/resources/testschema.json")));
    private Map<String, String> lookupPathsMap = new HashMap<>();
    private final Map deserializedSchema;

    public EventTransformerService() throws IOException {
        log.info("input schema string: {}", schemaStr);
        deserializedSchema = deserialize(schemaStr).as(LinkedHashMap.class);
//        prepareMissingNodeReferenceMap(deserializedSchema, "");
    }

    public String transformEvent(String inputJson) {
        /*final Any deserializeInput = deserialize(inputJson);
        final LinkedHashMap schemaCopy = new LinkedHashMap(deserializedSchema);
        lookupPathsMap.forEach((schemaPath, lookupPath) -> {
            Any eventPathValue = deserializeInput.get(lookupPath.split("\\."));
            String pathArray[] = schemaPath.split("\\.");

            if(pathArray.length>1) {
                Map innerMap = (Map) schemaCopy.get(pathArray[0]);
                final int maxLevel = pathArray.length - 1;
                for(int i = 1; i< maxLevel; i++) {
                    innerMap = (Map) innerMap.get(pathArray[i]);
                }
                innerMap.put(pathArray[maxLevel], eventPathValue);
            } else {
                schemaCopy.put(schemaPath, eventPathValue);
            }

        });

        log.info("final event: {}", schemaCopy);

        return JsonStream.serialize(schemaCopy);*/
        return inputJson;
    }

}
