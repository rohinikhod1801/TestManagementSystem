package com.bnt.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
	
	@Bean
	public RestTemplate restTemplateBean() {
	        return new RestTemplate();
	}

}
