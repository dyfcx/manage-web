package com.bder.manage.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Felix YF Dong
 * @date 2020/11/18
 */
@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping("")
    public Mono<String> mainRequest() {
        return Mono.just("Hello, Wang a pang.");
    }
}
