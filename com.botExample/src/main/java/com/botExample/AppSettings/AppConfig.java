package com.botExample.AppSettings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@ComponentScan(basePackages = 
{
	"com.bot*"
}
)
public class AppConfig {
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	public WebClient client() {
	    return WebClient.create("https://www.rail.co.il");
	}
	
	
//	@Bean(name = "messageResourceSB")
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource
//                = new ReloadableResourceBundleMessageSource();
//
//        messageSource.setBasename("messages/messages");
//        messageSource.setUseCodeAsDefaultMessage(true);
//        return messageSource;
//    }

}
