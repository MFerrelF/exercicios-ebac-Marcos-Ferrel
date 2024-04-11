/**
 * 
 */
package marcos.ferrel.sales.register.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import marcos.ferrel.sales.register.domain.Product;
import marcos.ferrel.sales.register.domain.Product.Status;
import marcos.ferrel.sales.register.exception.EntityNotFoundException;
import marcos.ferrel.sales.register.repository.IProductRepository;

/**
 * @author marcos.ferrel
 * 
 */
@Service
public class FindProduct {
	
	private IProductRepository productRepository;
	
	@Autowired
	public FindProduct(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Page<Product> find(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	public Page<Product> find(Pageable pageable, Status status) {
		return productRepository.findAllByStatus(pageable, status);
	}
	
	public Product searchByCode(String code) {
		return productRepository.findByCode(code)
				.orElseThrow(() -> new EntityNotFoundException(Product.class, "code", code));
	}

}
