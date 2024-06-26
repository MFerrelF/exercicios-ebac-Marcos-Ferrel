/**
 * 
 */
package marcos.ferrel.sales.register.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author marcos.ferrel
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {
	
	private String object;
	
	private String field;
	
	private Object rejectedValue;
	
	private String message;
	
	ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}

}
