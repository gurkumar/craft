package craft.demo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import craft.demo.dao.FormattingRepository;
import craft.demo.dao.FormattingRepositoryImpl;

@Configuration
//@EnableJpaRepositories(basePackages="craft.demo.dao")
//@EntityScan(basePackages = {"craft.demo.model"}) 
public class RepositoryConfiguration  {
	
	   
	@Bean 
	public FormattingRepository customUserRepository(){
	      return new FormattingRepositoryImpl ();
	   }

}
