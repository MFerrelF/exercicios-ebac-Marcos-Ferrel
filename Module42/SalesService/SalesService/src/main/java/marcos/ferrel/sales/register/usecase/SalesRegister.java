/**
 * 
 */
package marcos.ferrel.sales.register.usecase;

import java.math.BigDecimal;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import marcos.ferrel.sales.register.domain.Product;
import marcos.ferrel.sales.register.domain.Sales;
import marcos.ferrel.sales.register.domain.Sales.Status;
import marcos.ferrel.sales.register.dto.SalesDTO;
import marcos.ferrel.sales.register.exception.EntityNotFoundException;
import marcos.ferrel.sales.register.repository.ISalesRepository;
import marcos.ferrel.sales.register.service.CustomerService;
import marcos.ferrel.sales.register.service.IProductService;

/**
 * @author marcos.ferrel
 * 
 */
@Service
public class SalesRegister {
	
	private ISalesRepository salesRepository;
	
	private IProductService productService;
	
	private CustomerService customerService;
	
	@Autowired
	public SalesRegister(ISalesRepository productRepository, 
			IProductService productService, 
			CustomerService customerService) {
		this.salesRepository = productRepository;
		this.productService = productService;
		this.customerService = customerService;
	}
	
	public Sales register(@Valid SalesDTO salesDTO) {
		Sales sales = convertToDomain(salesDTO, Status.STARTED);
		validCustomer(sales.getCustomerId());
		sales.recalculateTotalSalesPrice();
		return this.salesRepository.insert(sales);
	}
	
	public Sales update(@Valid Sales sales) {
		return this.salesRepository.save(sales);
	}
	
	public Sales finish(String id) {
		Sales sales = findSales(id);
		sales.validStatus();
		sales.setStatus(Status.CONCLUDED);
		return this.salesRepository.save(sales);
	}
	
	public Sales cancel(String id) {
		Sales sales = findSales(id);
		sales.validStatus();
		sales.setStatus(Status.CANCELED);
		return this.salesRepository.save(sales);	
	}
	
	public Sales addProduct(String id, String productCode, Integer quantity) {
		Sales sales = findSales(id);
		Product product = findProduct(productCode);
		sales.validStatus();
		sales.addProduct(product, quantity);
		return this.salesRepository.save(sales);
	}
	
	public Sales removeProduct(String id, String productCode, Integer quantity) {
		Sales sales = findSales(id);
		Product product = findProduct(productCode);
		sales.validStatus();
		sales.removeProduct(product, quantity);
		return this.salesRepository.save(sales);
	}
	
	private Sales convertToDomain(@Valid SalesDTO salesDTO, Status status) {
		return Sales.builder()
				.customerId(salesDTO.getCustomerId())
				.code(salesDTO.getCode())
				.salesDate(salesDTO.getSalesDate())
				.status(status)
				.totalPrice(BigDecimal.ZERO)
				.products(new HashSet<>())
				.build();
	}
	
	private void validCustomer(String customerId) {
		Boolean isRegistered = this.customerService.isCustomerRegistered(customerId);
		if(!isRegistered) {
			new EntityNotFoundException(Sales.class, "customerId", customerId);
		}
	}
	
	private Sales findSales(String id) {
		return salesRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Sales.class, "id", id));
	}
	
	private Product findProduct (String productCode) {
		Product prod = productService.findProduct(productCode);
		if(prod == null) {
			throw new EntityNotFoundException(Sales.class, "code", productCode);
		}
		return prod;
	}

}
