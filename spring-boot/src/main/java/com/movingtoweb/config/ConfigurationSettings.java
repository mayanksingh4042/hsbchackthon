package com.movingtoweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationSettings {

   
    private String name;

    
    public String getName() {
        return name;
    }

}
