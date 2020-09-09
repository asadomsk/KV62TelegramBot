package com.botExample.AppSettings;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = 
{
	"com.bot*"
}
)
public class AppConfig {
	
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
