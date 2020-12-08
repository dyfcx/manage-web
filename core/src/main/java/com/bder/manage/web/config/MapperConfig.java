package com.bder.manage.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author Felix YF Dong
 * @date 2020/12/8
 */
@Configuration
public class MapperConfig {

//    @Bean("xmlMapper")
//    public XmlMapper xmlMapper(Jackson2ObjectMapperBuilder builder) {
//        return builder.createXmlMapper(true).build();
//    }

    @Bean("objectMapper")
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.createXmlMapper(false).build();
    }
}
