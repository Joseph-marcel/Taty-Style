package com.beauty.taty_style.models;


import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	private double  quantity;
	private double  amount;
	@Enumerated(EnumType.STRING)
	private OperationType type;
	@ManyToOne
	private Stock   stock;
	@OneToOne(mappedBy = "stockOperation")
	private Product product;
	
}
