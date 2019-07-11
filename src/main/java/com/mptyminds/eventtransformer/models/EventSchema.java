package com.mptyminds.eventtransformer.models;


import com.jsoniter.any.Any;
import com.mptyminds.eventtransformer.util.JsonUtil;
import lombok.Getter;

@Getter
public class EventSchema {

    final private String eventName;
    final private String rawEventSchemaString;
    final private Any eventDeserializedObject;

    public EventSchema(String eventName, String rawEventSchemaString) {
        this.eventName = eventName;
        this.rawEventSchemaString = rawEventSchemaString;
        this.eventDeserializedObject = JsonUtil.parseStringToAny(rawEventSchemaString);
    }
}
