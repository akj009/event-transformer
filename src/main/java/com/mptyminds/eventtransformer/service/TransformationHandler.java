package com.mptyminds.eventtransformer.service;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TransformationHandler {

    @Autowired
    private EventSourceService eventSourceService;

    @Autowired
    private SchemaService schemaService;

    public String handleEventTransformation(String inputEventStr, String sourceId) {
        Any deserInputObject = JsonIterator.deserialize(inputEventStr);
        String eventName = eventSourceService.parseEventName(deserInputObject, sourceId);
        Any eventSchema = schemaService.getEventSchema(eventName, sourceId);
        return "done: " + inputEventStr;
    }
}
