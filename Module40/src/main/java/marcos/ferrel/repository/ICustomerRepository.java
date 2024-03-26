/**
 * 
 */
package marcos.ferrel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import marcos.ferrel.domain.Customer;

/**
 * @author marcos.ferrel
 * 
 */
@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {

}
