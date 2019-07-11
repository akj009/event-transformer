package com.mptyminds.eventtransformer.service;

import com.jsoniter.any.Any;
import com.mptyminds.eventtransformer.config.ConfigurationFactory;
import com.mptyminds.eventtransformer.util.JsonUtil;
import org.springframework.stereotype.Service;

@Service
public class EventSourceService {
    public String parseEventName(Any inputEventStr, String sourceId) {
        String eventNamePaths = ConfigurationFactory.eventSourceNamePathMap.get(sourceId);
        return JsonUtil.findStringOnPath(eventNamePaths, inputEventStr);
    }
}
