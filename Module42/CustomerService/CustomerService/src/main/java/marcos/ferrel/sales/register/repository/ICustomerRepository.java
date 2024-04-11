/**
 * 
 */
package marcos.ferrel.sales.register.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import marcos.ferrel.sales.register.domain.Customer;

/**
 * @author marcos.ferrel
 * 
 */
@Repository
public interface ICustomerRepository extends MongoRepository<Customer, String> {
	
	Optional<Customer> findByCpf(Long cpf);

}
