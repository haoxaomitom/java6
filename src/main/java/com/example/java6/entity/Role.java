package com.example.java6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role  implements Serializable{
	@Id
	private String id;
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	List<Authority> authorities;
}