package com.mptyminds.eventtransformer.service;

import com.jsoniter.ValueType;
import com.jsoniter.any.Any;
import com.jsoniter.output.JsonStream;
import lombok.extern.log4j.Log4j2;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.jsoniter.JsonIterator.deserialize;

@Log4j2
public class EventTransformerService {


    public static String transformEvent(String inputJson, Map targetJsonSchemaMap, Map<String, String> lookupPathsMap) {
        final Any deserializeInput = deserialize(inputJson);
        final LinkedHashMap schemaCopy = (LinkedHashMap) targetJsonSchemaMap;
        lookupPathsMap.forEach((schemaPath, lookupPath) -> {
            Any eventPathValue = deserializeInput.get(lookupPath.split("\\."));
            if(eventPathValue.valueType() == ValueType.INVALID) {
                log.info("null");
                eventPathValue = Any.wrap("");
            }

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

        return JsonStream.serialize(schemaCopy);
    }



}
