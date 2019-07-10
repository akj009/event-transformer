package com.mptyminds.eventtransformer.api;

import com.mptyminds.eventtransformer.service.TransformationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transform")
public class InputApi {

    private final TransformationHandler transformationHandler;

    @Autowired
    public InputApi(TransformationHandler transformationHandler) {
        this.transformationHandler = transformationHandler;
    }

    @PostMapping(value = "/one/{sourceId}")
    public ResponseEntity<String> transformSingleJson(@RequestBody String inputJson, @PathVariable String sourceId) {

        String transformedJson = transformationHandler.handleEventTransformation(inputJson, sourceId);

        return ResponseEntity.ok(transformedJson);
    }
}
