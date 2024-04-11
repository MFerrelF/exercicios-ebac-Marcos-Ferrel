/**
 * 
 */
package marcos.ferrel.sales.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.Validator;

/**
 * @author marcos.ferrel
 * 
 */
@Configuration
public class ValidatorConfig {
	
	@Bean
	public Validator validatorFactory() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		return bean;
	}

}
