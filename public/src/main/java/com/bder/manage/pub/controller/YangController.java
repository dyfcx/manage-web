package com.bder.manage.pub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Felix YF Dong
 * @date 2020/11/18
 */
@RestController
@RequestMapping("/yang")
public class YangController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hello, Wang a pang.");
    }
}
