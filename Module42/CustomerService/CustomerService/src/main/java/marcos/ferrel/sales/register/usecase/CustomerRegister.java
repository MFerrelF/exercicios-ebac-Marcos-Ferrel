/**
 * 
 */
package marcos.ferrel.sales.register.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import marcos.ferrel.sales.register.domain.Customer;
import marcos.ferrel.sales.register.repository.ICustomerRepository;

/**
 * @author marcos.ferrel
 * 
 */
@Service
public class CustomerRegister {
	
	private ICustomerRepository customerRepository;
	
	@Autowired
	public CustomerRegister(ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Customer register(@Valid Customer customer) {
		return this.customerRepository.insert(customer);
	}
	
	public Customer update(@Valid Customer customer) {
		return this.customerRepository.save(customer);
	}
	
	public void remove(String id) {
		this.customerRepository.deleteById(id);
	}

}
