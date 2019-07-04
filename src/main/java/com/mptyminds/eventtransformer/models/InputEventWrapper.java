package com.mptyminds.eventtransformer.models;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InputEventWrapper {

    private EventSource eventSource;
    private Instant arrivalTime;
    private String payload;
    private List<EventDestination> destinationsList;
    private String eventNamePaths;
}
