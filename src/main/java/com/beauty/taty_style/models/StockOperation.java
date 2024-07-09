package com.beauty.taty_style.models;


import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public  class StockOperation {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long    number;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date    dateOperation;
	private double  amount;
	private double  quantity;
	private OperationType type;
	@ManyToOne
	private Stock   stock;
	@OneToMany(mappedBy = "stockOperation")
	List<Product> products;
}
