package com.beauty.taty_style.models;


import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
public class Stock {

	@Id
	private String  reference;
	private String  title;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date    dateExistant;
	private double  niveauStock;
	@Enumerated(EnumType.STRING)
	private StockStatus  status;
	@Column(name="value_stock")
	private double  valueStock;
	@OneToMany(mappedBy = "stock")
	private List<StockOperation> stockOperations;
}
