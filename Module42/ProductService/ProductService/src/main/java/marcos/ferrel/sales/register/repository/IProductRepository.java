/**
 * 
 */
package marcos.ferrel.sales.register.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import marcos.ferrel.sales.register.domain.Product;
import marcos.ferrel.sales.register.domain.Product.Status;

/**
 * @author marcos.ferrel
 * 
 */
@Repository
public interface IProductRepository extends MongoRepository<Product, String> {
	
	Optional<Product> findByCode(String code);
	
	Page<Product> findAllByStatus(Pageable pageable, Status status);

}
