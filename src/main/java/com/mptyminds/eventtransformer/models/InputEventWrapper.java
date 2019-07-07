package com.mptyminds.eventtransformer.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InputEventWrapper {

    private EventSource eventSource;
    private String arrivalTime;
    private String payload;
    private List<EventDestination> destinationsList;
    private String eventNamePaths;
}
