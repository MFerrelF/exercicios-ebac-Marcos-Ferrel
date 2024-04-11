/**
 * 
 */
package marcos.ferrel.sales.register.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import marcos.ferrel.sales.register.domain.Product;

/**
 * @author marcos.ferrel
 * 
 */
@FeignClient(name = "product", url = "${application.produtoService.endpointConsultProduct}")
public interface IProductService {
	
	@RequestMapping(method = RequestMethod.GET, value = "/{code}", produces = "application/json", headers = "application/json")
	Product findProduct(@RequestParam("code") String productCode);
}
