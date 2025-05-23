package com.StarkIndustries.RadientDermat.cloudinary.configuration;

import com.StarkIndustries.RadientDermat.keys.Keys;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfiguration {

    @Value("${CLOUD_NAME}")
//    public  String CLOUD_NAME;
//
//    @Value("${API_KEY}")
//    public  String API_KEY;
//
//    @Value("${API_SECRETE}")
//    public  String API_SECRETE;

    @Bean
    public Cloudinary getCloudinaryConfigurations(){
        Map<String,Object> configurations = new HashMap<>();
        configurations.put(Keys.CLOUD_NAME,"dhdrzsxor");
        configurations.put(Keys.API_KEY,"316185456453451");
        configurations.put(Keys.API_SECRETE,"QrkDeLlcsz9z2maFx1grSIE4wDA");
        configurations.put(Keys.SECURE,true);
        return new Cloudinary(configurations);
    }
}
