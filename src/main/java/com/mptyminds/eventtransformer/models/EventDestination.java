package com.mptyminds.eventtransformer.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDestination {
    private String destinationName;
    private DestinationTypeEnum destinationType;
}
