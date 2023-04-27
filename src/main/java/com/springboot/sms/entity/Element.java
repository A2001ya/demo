package com.springboot.sms.entity;

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
public class Element {

	@Id
	@GeneratedValue
	private int id;
	@Column
	private String NomElement;
	@ManyToOne
	@JoinColumn(name="nomModule")
	private Module module;
	@Column
	private int coef;
	@ManyToOne
	@JoinColumn(name="nomFiliere")
	private Filiere filiere;
	public Element() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Element(int id, String nomElement, Module module, int coef, Filiere filiere) {
		super();
		this.id = id;
		NomElement = nomElement;
		this.module = module;
		this.coef = coef;
		this.filiere = filiere;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomElement() {
		return NomElement;
	}
	public void setNomElement(String nomElement) {
		NomElement = nomElement;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public int getCoef() {
		return coef;
	}
	public void setCoef(int coef) {
		this.coef = coef;
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	
}
