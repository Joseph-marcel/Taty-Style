package com.beauty.taty_style.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pack {

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long packId;
	@ManyToMany(mappedBy = "packs",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Allowance> allowances = new ArrayList<Allowance>();
	@OneToMany(mappedBy = "pack")
	@JsonIgnore
	private List<Bill> bills = new ArrayList<Bill>();
}
