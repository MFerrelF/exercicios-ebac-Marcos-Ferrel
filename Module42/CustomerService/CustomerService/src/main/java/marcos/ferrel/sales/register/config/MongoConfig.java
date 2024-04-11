/**
 * 
 */
package marcos.ferrel.sales.register.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author marcos.ferrel
 * 
 */
@Configuration
@EnableMongoRepositories(basePackages = "marcos.ferrel.sales.register.repository")
public class MongoConfig {

}
