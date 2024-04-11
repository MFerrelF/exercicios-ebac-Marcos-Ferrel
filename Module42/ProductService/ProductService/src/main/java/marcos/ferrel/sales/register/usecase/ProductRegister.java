/**
 * 
 */
package marcos.ferrel.sales.register.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import marcos.ferrel.sales.register.domain.Product;
import marcos.ferrel.sales.register.domain.Product.Status;
import marcos.ferrel.sales.register.exception.EntityNotFoundException;
import marcos.ferrel.sales.register.repository.IProductRepository;

/**
 * @author marcos.ferrel
 * 
 */
@Service
public class ProductRegister {
	
	private IProductRepository productRepository;
	
	@Autowired
	public ProductRegister(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Product register(@Valid Product product) {
		product.setStatus(Status.ACTIVE);
		return this.productRepository.insert(product);
	}
	
	public Product update(@Valid Product product) {
		return this.productRepository.save(product);
	}
	
	public void remove(String id) {
		Product prod = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Product.class, "id", id));
		prod.setStatus(Status.INACTIVE);
		this.productRepository.save(prod);
	}

}
