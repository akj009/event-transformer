package com.mptyminds.eventtransformer.api;

import com.mptyminds.eventtransformer.service.TransformationHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
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

        log.info("incoming event: {}", inputJson);
        String transformedJson = transformationHandler.handleEventTransformation(inputJson, sourceId);

        return ResponseEntity.ok(transformedJson);
    }
}
