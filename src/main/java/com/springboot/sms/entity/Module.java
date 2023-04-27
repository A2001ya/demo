package com.springboot.sms.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {

	@Id
	@GeneratedValue
	private int id;
	@Column
	private String nomModule;
	@ManyToOne
	@JoinColumn(name="nomFiliere")
	private Filiere F;
	
	public Module(int id, String nomModule, Filiere f) {
		super();
		this.id = id;
		this.nomModule = nomModule;
		F = f;
	}
	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomModule() {
		return nomModule;
	}
	public void setNomModule(String nomModule) {
		this.nomModule = nomModule;
	}
	public Filiere getF() {
		return F;
	}
	public void setF(Filiere f) {
		F = f;
	}
	
}
