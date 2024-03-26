/**
 * 
 */
package marcos.ferrel.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author marcos.ferrel
 * 
 */
@Entity
@Table(name = "TB_CUSTOMER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_seq")
	@SequenceGenerator(name="customer_seq", sequenceName="sq_customer", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "NAME", nullable = false, length = 50)
	private String name;
	
	@Column(name = "CPF", nullable = false, unique = true)
	private Long cpf;
	
	@Column(name = "PHONE_NUMBER", nullable = false)
	private Long pNumber;
	
	@Column(name = "EMAIL", nullable = false, length = 50)
	private String email;
	
	@Column(name = "ADDRESS", nullable = false, length = 100)
	private String address;
	
	@Column(name = "NUMBER", nullable = false)
	private Integer number;
	
	@Column(name = "CITY", nullable = false, length = 50)
	private String city;
	
	@Column(name = "STATE", nullable = false, length = 50)
	private String state;	

}
