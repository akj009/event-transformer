package com.mptyminds.eventtransformer.service;

import org.springframework.stereotype.Component;

@Component
public class TransformationHandler {


    public String handleEventTransformation(String inputEventStr) {

        return "done: " ;//+ someKey;
    }
}
