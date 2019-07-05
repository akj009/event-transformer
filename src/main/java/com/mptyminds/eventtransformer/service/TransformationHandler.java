package com.mptyminds.eventtransformer.service;

import com.jsoniter.JsonIterator;
import com.mptyminds.eventtransformer.models.InputEventWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TransformationHandler {

    @Value("${app.greet.name}")
    private String someKey;

    public String handleEventTransformation(String inputEventStr) {

        final InputEventWrapper inputEventWrapper = JsonIterator.deserialize(inputEventStr).as(InputEventWrapper.class);


        return "done: " + someKey;
    }
}
