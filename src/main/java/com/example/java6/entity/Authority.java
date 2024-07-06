package com.example.java6.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Authorities", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"Username", "Roleid"})
})
public class Authority  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne @JoinColumn(name = "Username")
	private Account account;
	@ManyToOne  @JoinColumn(name = "Roleid")
	private Role role;
}