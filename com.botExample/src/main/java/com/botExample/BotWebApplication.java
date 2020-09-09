package com.botExample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = 
{
	"com.bot*"
}
)
public class BotWebApplication {
    
	public static void main(String[] args) throws Throwable {
		SpringApplication.run(BotWebApplication.class, args);

	}
	 

}