/**
 * 
 */
package marcos.ferrel.sales.register.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "sales")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Sales {
	
	public enum Status {
		STARTED, CONCLUDED, CANCELED;
		
		public static Status getByName(String value) {
			for(Status status : Status.values()) {
				if(status.name().equals(value)) {
					return status;
				}
			}
			return null;
		}
	}
	
	@Id
	private String id;
	
	@NotNull
	@Size(min = 2, max = 10)
	@Indexed(unique = true, background = true)
	private String code;
	
	@NotNull
	private String customerId;
	
	private Set<QuantityProduct> products;
	
	private BigDecimal totalPrice;
	
	@NotNull
	private Instant salesDate;
	
	@NotNull
	private Status status;
	
	public Sales() {
		products = new HashSet<>();
	}
	
	public void addProduct(Product product, Integer quantity) {
		validStatus();
		Optional<QuantityProduct> opt = 
				products.stream().filter(filter -> filter.getProduct().getCode().equals(product.getCode())).findAny();
		if(opt.isPresent()) {
			QuantityProduct quantityProd = opt.get();
			quantityProd.add(quantity);
		} else {
			QuantityProduct prod = 
					QuantityProduct.builder()
					.product(product)
					.totalPrice(BigDecimal.ZERO)
					.quantity(0)
					.build();
			prod.add(quantity);
			products.add(prod);
		}
		recalculateTotalSalesPrice();
	}
	
	public void removeProduct(Product product, Integer quantity) {
		validStatus();
		Optional<QuantityProduct> opt = 
				products.stream().filter(filter -> filter.getProduct().getCode().equals(product.getCode())).findAny();
		if(opt.isPresent()) {
			QuantityProduct quantityProd = opt.get();
			if(quantityProd.getQuantity() > quantity) {
				quantityProd.remove(quantity);
				recalculateTotalSalesPrice();
			} else {
				products.remove(opt.get());
				recalculateTotalSalesPrice();
			}
		}
	}
	
	public void removeAllProd() {
		validStatus();
		products.clear();
		totalPrice = BigDecimal.ZERO;
	}
	
	public Integer getTotalQuantityProducts() {
		int result = products.stream()
				.reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantity(), Integer::sum);
		return result;
	}
	
	public void validStatus() {
		if(this.status == Status.CONCLUDED || this.status == Status.CANCELED) {
			throw new UnsupportedOperationException("Impossible to update. Sales is Concluded or Canceled");	
		}
	}
	
	public void recalculateTotalSalesPrice() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for(QuantityProduct prod : this.products) {
			totalPrice = totalPrice.add(prod.getTotalPrice());
		}
		this.totalPrice = totalPrice;
	}

}
