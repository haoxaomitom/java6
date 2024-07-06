package com.example.java6.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orderdetails")
public class OrderDetail  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Double price;
	Integer quantity;
	@ManyToOne
	@JoinColumn(name = "Productid")
	Product product;
	@ManyToOne
	@JoinColumn(name = "Orderid")
	Order order;
}
