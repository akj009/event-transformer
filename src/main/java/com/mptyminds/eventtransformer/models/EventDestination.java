package com.mptyminds.eventtransformer.models;

import com.mptyminds.eventtransformer.constants.DestinationTypeEnum;
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
