package com.beauty.taty_style.models;


import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name="TYPE", discriminatorType = DiscriminatorType.STRING,length=4)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Allowance {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long number;
	private String name;
	@ManyToOne
	private Pack pack;
}
