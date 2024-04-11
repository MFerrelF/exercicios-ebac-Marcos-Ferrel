/**
 * 
 */
package marcos.ferrel.sales.register.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author marcos.ferrel
 * 
 */
@Service
public class CustomerService {
	
	@Value("${application.customerService.endpointConsultCustomer}")
	private String urlEndpointConsultCustomer;
	
	private RestUtils restUtils;
	
	@Autowired
	public CustomerService(RestUtils restUtils) {
		this.restUtils = restUtils;
	}
	
	public Boolean isCustomerRegistered(String customerId) {
		RestRequest restRequest = new RestRequest(HttpMethod.GET, null);
		restRequest.setContentType(MediaType.APPLICATION_JSON);
		restRequest.setAcceptable(Collections.singletonList(MediaType.APPLICATION_JSON));
		String urlComParam = urlEndpointConsultCustomer.replace("{id}", customerId);
		ResponseEntity<Boolean> response = restUtils.execute(urlComParam, restRequest, Boolean.class);
		return response.getBody();
	}

}
