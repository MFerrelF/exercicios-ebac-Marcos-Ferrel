/**
 * 
 */
package marcos.ferrel.sales.register.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import marcos.ferrel.sales.register.domain.Sales;
import marcos.ferrel.sales.register.exception.EntityNotFoundException;
import marcos.ferrel.sales.register.repository.ISalesRepository;

/**
 * @author marcos.ferrel
 * 
 */
@Service
public class FindSales {
	
	private ISalesRepository salesRepository;
	
	@Autowired
	public FindSales(ISalesRepository productRepository) {
		this.salesRepository = productRepository;
	}
	
	public Page<Sales> find(Pageable pageable) {
		return salesRepository.findAll(pageable);
	}
	
	public Sales searchByCode(String code) {
		return salesRepository.findByCode(code)
				.orElseThrow(() -> new EntityNotFoundException(Sales.class, "code", code));
	}

}
