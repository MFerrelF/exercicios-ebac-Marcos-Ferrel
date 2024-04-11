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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import marcos.ferrel.sales.register.domain.Product;
import marcos.ferrel.sales.register.domain.Product.Status;
import marcos.ferrel.sales.register.usecase.FindProduct;
import marcos.ferrel.sales.register.usecase.ProductRegister;

/**
 * @author marcos.ferrel
 * 
 */
@RestController
@RequestMapping(value = "/product")
public class ProductResource {
	
	private FindProduct findProduct;
	
	private ProductRegister productRegister;
	
	@Autowired
	public ProductResource(FindProduct findProduct, ProductRegister productRegister) {
		this.findProduct = findProduct;
		this.productRegister = productRegister;
	}
	
	@GetMapping
	@Operation(summary = "Find a paged list of products")
	public ResponseEntity<Page<Product>> find(Pageable pageable) {
		return ResponseEntity.ok(findProduct.find(pageable));
	}
	
	@GetMapping(value = "/status/{status}")
	@Operation(summary = "Find a paged list of products by their status")
	public ResponseEntity<Page<Product>> findByStatus(Pageable pageable, 
			@PathVariable(value = "status", required = true) Status status) {
		return ResponseEntity.ok(findProduct.find(pageable, status));
	}
	
	@GetMapping(value = "/{code}")
	@Operation(summary = "Find a paged list of products by their code")
	public ResponseEntity<Product> findByCode(String code) {
		return ResponseEntity.ok(findProduct.searchByCode(code));
	}
	
	@PostMapping
	@Operation(summary = "Register a product")
	public ResponseEntity<Product> register(@RequestBody @Valid Product product) {
		return ResponseEntity.ok(productRegister.register(product));
	}
	
	@PostMapping
	@Operation(summary = "Update a product")
	public ResponseEntity<Product> update(@RequestBody @Valid Product product) {
		return ResponseEntity.ok(productRegister.update(product));
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remove a product by its unique identifier")
	public ResponseEntity<String> remove(@PathVariable(value = "id")String id) {
		productRegister.remove(id);
		return ResponseEntity.ok("Product deleted");
	}	

}
