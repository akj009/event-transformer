package com.mptyminds.eventtransformer.api;

import com.mptyminds.eventtransformer.service.EventTransformer;
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
    private EventTransformer eventTransformer;

    @PostMapping(value = "/one")
    public Mono<ResponseEntity<String>> transformSingleJson(@RequestBody String inputJson) {

        String transformedJson = eventTransformer.transformEvent(inputJson);

        return Mono.just(ResponseEntity.ok(transformedJson));
    }
}
