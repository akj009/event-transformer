package com.mptyminds.eventtransformer.service;

import com.jsoniter.JsonIterator;
import com.mptyminds.eventtransformer.models.InputEventWrapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TransformationHandler {

    public Mono<String> handleEventTransformation(String inputEventStr) {

        final InputEventWrapper inputEventWrapper = JsonIterator.deserialize(inputEventStr).as(InputEventWrapper.class);



    }
}
