package craft.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import craft.demo.formatter.MarkdownToHtmlConvertingService;
import craft.demo.formatter.MarkdownToHtmlConvertingServiceImpl;

@SpringBootApplication
@EnableAutoConfiguration
//@EntityScan(basePackages = {"craft.demo.model"}) 
//@EnableJpaRepositories(basePackages="craft.demo.dao")
public class DemoApp {

	public static void main(String[] args) {
      SpringApplication.run(DemoApp.class, args);
    }
	
	
	
	
  
}