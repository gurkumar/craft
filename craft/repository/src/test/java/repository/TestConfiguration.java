package repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@EnableJpaRepositories(basePackages="craft.demo.dao")
@EntityScan(basePackages = {"craft.demo.model"}) 
@ActiveProfiles(profiles = "test")
public class TestConfiguration{

	
}
