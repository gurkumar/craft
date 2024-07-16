package service;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import craft.demo.config.ServiceConfiguration;

@Configuration
@ActiveProfiles(profiles = "test")
public class TestConfiguration extends ServiceConfiguration{

	
}
