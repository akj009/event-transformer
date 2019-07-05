package com.mptyminds.eventtransformer.api;

import com.mptyminds.eventtransformer.service.EventTransformerService;
import com.mptyminds.eventtransformer.service.TransformationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transform")
public class InputApi {

    @Autowired
    private TransformationHandler transformationHandler;

    @PostMapping(value = "/one")
    public Mono<ResponseEntity<String>> transformSingleJson(@RequestBody String inputJson) {

        Mono<String> transformedJson = transformationHandler.handleEventTransformation(inputJson);

        return transformedJson.map(ResponseEntity::ok);
    }
}
