package com.qa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Configuration
public class AppConfig {
	
	@GetMapping("/")
	public ModelAndView index() {
	    return new ModelAndView("index.html");
	}
	
	@Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
	
}
