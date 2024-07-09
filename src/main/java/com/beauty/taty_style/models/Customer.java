package com.beauty.taty_style.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long number;
	private String name;
	private String phoneNumber;
	@OneToMany(mappedBy = "customer")
	private List<Bill> bills;
}
