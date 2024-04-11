/**
 * 
 */
package marcos.ferrel.sales.register.domain;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author marcos.ferrel
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	
	private String id;
	
	@NotNull
	@Size(min = 2, max = 10)
	private String code;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String description;
	
	@NotNull
	private BigDecimal price;

}
