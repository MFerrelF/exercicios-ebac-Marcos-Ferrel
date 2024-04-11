/**
 * 
 */
package marcos.ferrel.sales.register.domain;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
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
public class QuantityProduct {
	
	@NotNull
	private Product product;
	
	@NotNull
	private Integer quantity;
	
	private BigDecimal totalPrice;
	
	public QuantityProduct() {
		this.quantity = 0;
		this.totalPrice = BigDecimal.ZERO;
	}
	
	public void add(Integer quantity) {
		this.quantity += quantity;
		BigDecimal newPrice = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
		BigDecimal newTotal = this.totalPrice.add(newPrice);
		this.totalPrice = newTotal;
	}
	
	public void remove(Integer quantity) {
		this.quantity -= quantity;
		BigDecimal newPrice = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
		this.totalPrice = this.totalPrice.subtract(newPrice);
	}

}
