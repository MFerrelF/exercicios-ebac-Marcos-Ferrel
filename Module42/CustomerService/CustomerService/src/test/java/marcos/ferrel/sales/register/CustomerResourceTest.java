/**
 * 
 */
package marcos.ferrel.sales.register;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import marcos.ferrel.sales.register.domain.Customer;
import marcos.ferrel.sales.register.resources.CustomerResource;
import marcos.ferrel.sales.register.usecase.CustomerRegister;
import marcos.ferrel.sales.register.usecase.FindCustomer;

/**
 * @author marcos.ferrel
 * 
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerResourceTest {
	
	@InjectMocks
	private CustomerResource customerResource;
	
	@MockBean
	private FindCustomer findCustomer;
	
	@MockBean
	private CustomerRegister customerRegister;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void searchById() {
		Customer customer1 = Customer.builder().id("1").name("Marcos Ferrel").build();
		
		when(findCustomer.searchById("1")).thenReturn(customer1);
		
		ResponseEntity<Customer> result = customerResource.findById("1");
		
		Customer customerResult = result.getBody();
		assertThat(customerResult).isEqualTo(customer1);
		assertThat(customerResult.getId()).isEqualTo(customer1.getId());
		assertThat(customerResult.getName()).isEqualTo(customer1.getName());		
	}

}
