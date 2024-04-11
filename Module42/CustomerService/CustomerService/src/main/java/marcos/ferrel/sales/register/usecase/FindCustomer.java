/**
 * 
 */
package marcos.ferrel.sales.register.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import marcos.ferrel.sales.register.domain.Customer;
import marcos.ferrel.sales.register.exception.EntityNotFoundException;
import marcos.ferrel.sales.register.repository.ICustomerRepository;

/**
 * @author marcos.ferrel
 * 
 */
@Service
public class FindCustomer {
	
	private ICustomerRepository customerRepository;
	
	@Autowired
	public FindCustomer(ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Page<Customer> find(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}
	
	public Customer searchById(String id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Customer.class, "id", id));
	}
	
	public Boolean isRegistered(String id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.isPresent() ? true : false;
	}
	
	public Customer searchByCpf(Long cpf) {
		return customerRepository.findByCpf(cpf)
				.orElseThrow(() -> new EntityNotFoundException(Customer.class, "cpf", String.valueOf(cpf)));
	}

}
