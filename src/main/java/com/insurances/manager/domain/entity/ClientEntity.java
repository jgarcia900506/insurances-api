package com.insurances.manager.domain.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String dni;

	@Column
	private String name;

	@Column
	private String lastname;

	@Column
	private String email;

	@Column
	private String phone;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
	private UserEntity user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	private List<PolicyEntity> policies;
}
