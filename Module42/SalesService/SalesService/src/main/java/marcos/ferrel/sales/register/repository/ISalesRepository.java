/**
 * 
 */
package marcos.ferrel.sales.register.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import marcos.ferrel.sales.register.domain.Sales;

/**
 * @author marcos.ferrel
 * 
 */
@Repository
public interface ISalesRepository extends MongoRepository<Sales, String> {
	
	Optional<Sales> findByCode(String code);

}
