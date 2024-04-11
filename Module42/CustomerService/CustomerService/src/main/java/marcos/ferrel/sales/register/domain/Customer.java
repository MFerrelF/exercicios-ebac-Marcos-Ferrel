/**
 * 
 */
package marcos.ferrel.sales.register.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Document(collection = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name="Customer", description="Customer")
public class Customer {
	
	@Id
	@Schema(description="Unique Identifier")
	private String id;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description="Name", minLength = 1, maxLength = 50, nullable = false)
	private String name;
	
	@NotNull
	@Indexed(unique = true, background = true)
	@Schema(description="CPF", nullable = false)
	private Long cpf;
	
	@NotNull
	@Schema(description="Phone Number", nullable = false)
	private Long phoneNumber;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Indexed(unique = true, background = true)
	@Schema(description="Email", minLength = 1, maxLength = 50, nullable = false)
	@Pattern(regexp = ".+@.+\\..+", message = "Invalid email")
	private String email;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description="Address", minLength = 1, maxLength = 50, nullable = false)
	private String address;
	
	@NotNull
	@Schema(description="Residence Number", nullable = false)
	private Integer number;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description="City", minLength = 1, maxLength = 50, nullable = false)
	private String city;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description="State", minLength = 1, maxLength = 50, nullable = false)
	private String state;	
	
}
