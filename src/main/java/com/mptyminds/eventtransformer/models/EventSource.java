package com.mptyminds.eventtransformer.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventSource {
    private String sourceName;
    private List<String> eventNamePaths;
}
