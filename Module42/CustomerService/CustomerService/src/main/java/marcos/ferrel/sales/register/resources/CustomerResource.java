/**
 * 
 */
package marcos.ferrel.sales.register.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import marcos.ferrel.sales.register.domain.Customer;
import marcos.ferrel.sales.register.usecase.CustomerRegister;
import marcos.ferrel.sales.register.usecase.FindCustomer;

/**
 * @author marcos.ferrel
 * 
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {
	
	private FindCustomer findCustomer;
	
	private CustomerRegister customerRegister;
	
	@Autowired
	public CustomerResource(FindCustomer findCustomer, CustomerRegister customerRegister) {
		this.findCustomer = findCustomer;
		this.customerRegister = customerRegister;
	}
	
	@GetMapping
	public ResponseEntity<Page<Customer>> find(Pageable pageable) {
		return ResponseEntity.ok(findCustomer.find(pageable));
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Find a customer by its ID")
	public ResponseEntity<Customer> findById(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(findCustomer.searchById(id));
	}
	
	@GetMapping(value = "isRegistered/{id}")
	public ResponseEntity<Boolean> isRegistered(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(findCustomer.isRegistered(id));
	}
	
	@PostMapping
	public ResponseEntity<Customer> register(@RequestBody @Valid Customer customer) {
		return ResponseEntity.ok(customerRegister.register(customer));
	}
	
	@GetMapping(value = "/cpf/{cpf}")
	@Operation(summary = "Find a customer by its cpf")
	public ResponseEntity<Customer> findByCpf(@PathVariable(value = "cpf", required = true) Long cpf) {
		return ResponseEntity.ok(findCustomer.searchByCpf(cpf));
	}
	
	@PutMapping
	@Operation(summary = "Update a customer")
	public ResponseEntity<Customer> update(@RequestBody @Valid Customer customer) {
		return ResponseEntity.ok(customerRegister.update(customer));
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remove a customer by its unique identifier")
	public ResponseEntity<String> remove(@PathVariable(value = "id") String id) {
		customerRegister.remove(id);
		return ResponseEntity.ok("Customer Removed");
	}

}
