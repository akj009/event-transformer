package com.mptyminds.eventtransformer.api;

import com.mptyminds.eventtransformer.service.TransformationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transform")
public class InputApi {

    private final TransformationHandler transformationHandler;

    @Autowired
    public InputApi(TransformationHandler transformationHandler) {
        this.transformationHandler = transformationHandler;
    }

    @PostMapping(value = "/one")
    public ResponseEntity<String> transformSingleJson(@RequestBody String inputJson) {

        String transformedJson = transformationHandler.handleEventTransformation(inputJson);

        return ResponseEntity.ok(transformedJson);
    }
}
