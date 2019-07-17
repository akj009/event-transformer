package com.mptyminds.eventtransformer.service;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.mptyminds.eventtransformer.models.EventSchema;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Log4j2
@Service
public class TransformationHandler {

    @Autowired
    private EventSourceService eventSourceService;

    @Autowired
    private SchemaService schemaService;

    public String handleEventTransformation(String inputEventStr, String sourceId) {
        Any deserInputObject = JsonIterator.deserialize(inputEventStr);
        String eventName = eventSourceService.parseEventName(deserInputObject, sourceId);
        if(!StringUtils.hasText(eventName)) {
            log.info("event name not found");
            return inputEventStr;
        }
        EventSchema eventSchemaMap = schemaService.getEventSchema(eventName, sourceId);
        return EventTransformerService.transformEvent(inputEventStr, eventSchemaMap.getEventDeserializedMap(), eventSchemaMap.getLookupPathsMap());
    }
}
