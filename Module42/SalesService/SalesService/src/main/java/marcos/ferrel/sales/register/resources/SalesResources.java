/**
 * 
 */
package marcos.ferrel.sales.register.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import marcos.ferrel.sales.register.domain.Sales;
import marcos.ferrel.sales.register.dto.SalesDTO;
import marcos.ferrel.sales.register.usecase.FindSales;
import marcos.ferrel.sales.register.usecase.SalesRegister;

/**
 * @author marcos.ferrel
 * 
 */
@RestController
@RequestMapping(value = "/sales")
public class SalesResources {
	
	private FindSales findSales;
	
	private SalesRegister salesRegister;
	
	@Autowired
	public SalesResources(FindSales findSales, SalesRegister salesRegister) {
		this.findSales = findSales;
		this.salesRegister = salesRegister;
	}
	
	@GetMapping
	@Operation(summary = "List registered sales")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returns customer list"),
		    @ApiResponse(responseCode = "400", description = "Bad request or sintaxe error", 
		    		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
		    @ApiResponse(responseCode = "500", description = "Executed",
		    		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
	})
	public ResponseEntity<Page<Sales>> find(Pageable pageable) {
		return ResponseEntity.ok(findSales.find(pageable));
	}
	
	@PostMapping
	@Operation(summary = "Stating sales")
	public ResponseEntity<Sales> register(@RequestBody @Valid SalesDTO sales) {
		return ResponseEntity.ok(salesRegister.register(sales));
	}
	
	@PutMapping("/{id}/{productCode}/{quantity}/addProduct")
	public ResponseEntity<Sales> addProduct(
			@PathVariable(name = "id", required = true) String id,
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "quantity", required = true) Integer quantity) {
		return ResponseEntity.ok(salesRegister.addProduct(id, productCode, quantity));
	}

}
