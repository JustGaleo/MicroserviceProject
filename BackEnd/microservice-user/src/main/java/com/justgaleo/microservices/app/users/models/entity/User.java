package com.justgaleo.microservices.app.users.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.justgaleo.microservices.app.funko.models.entity.Funko;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String lastName;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Funko> funkos;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@PrePersist
	public void prepersist() {
		this.createAt = new Date();
	}
	
	public User() {
		this.funkos = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Funko> getFunkos() {
		return funkos;
	}

	public void setFunkos(List<Funko> funkos) {
		this.funkos = funkos;
	}
	
	public void addFunko(Funko funko) {
		this.funkos.add(funko);
	}
	public void removeFunko(Funko funko) {
		this.funkos.remove(funko);
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof User)) {
			return false;
		}

		User a = (User) obj;

		return this.id != null && this.id.equals(a.getId());
	}

}
