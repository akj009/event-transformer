package com.mptyminds.eventtransformer.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class SourceEntity {

    @Id
    private String id;
    private String sourceName;
    private String sourceEventNamePaths;
}
