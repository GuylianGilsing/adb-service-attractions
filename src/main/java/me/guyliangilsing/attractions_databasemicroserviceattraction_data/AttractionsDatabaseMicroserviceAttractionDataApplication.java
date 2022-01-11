package me.guyliangilsing.attractions_databasemicroserviceattraction_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AttractionsDatabaseMicroserviceAttractionDataApplication
{
    @Bean
    public WebMvcConfigurer configureGlobalCORS()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("OPTIONS", "HEAD","GET","PUT","POST","DELETE","PATCH");
            }
        };
    }

	public static void main(String[] args)
    {
		SpringApplication.run(AttractionsDatabaseMicroserviceAttractionDataApplication.class, args);
	}
}
