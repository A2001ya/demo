package com.springboot.sms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
	
	@Id
	@GeneratedValue
	private int password;
	
	@Column(length = 45 , nullable = false)
	private String Username;
	
	
	
	@OneToOne
	@JoinColumn(name = "professeur_id")
	private professeur prof;



	public int getPassword() {
		return password;
	}



	public void setPassword(int password) {
		this.password = password;
	}



	public String getUsername() {
		return Username;
	}



	public void setUsername(String username) {
		Username = username;
	}



	public professeur getProf() {
		return prof;
	}



	public void setProf(professeur prof) {
		this.prof = prof;
	}

	
	

}
