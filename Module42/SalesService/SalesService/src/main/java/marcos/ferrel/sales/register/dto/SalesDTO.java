/**
 * 
 */
package marcos.ferrel.sales.register.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author marcos.ferrel
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SalesDTO {
	
	@NotNull
	@Size(min = 2, max = 10)
	private String code;
	
	@NotNull
	private String customerId;
	
	@NotNull
	private Instant salesDate;

}
