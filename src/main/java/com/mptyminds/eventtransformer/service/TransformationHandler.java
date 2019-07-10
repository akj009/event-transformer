package com.mptyminds.eventtransformer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TransformationHandler {

    @Autowired
    private EventSourceService eventSourceService;

    public String handleEventTransformation(String inputEventStr, String sourceId) {
        String eventName = eventSourceService.parseEventName(inputEventStr, sourceId);
        return "done: " + inputEventStr;
    }
}
