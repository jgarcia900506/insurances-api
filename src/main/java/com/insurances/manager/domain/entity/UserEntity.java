package com.insurances.manager.domain.entity;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "user_authorities",
		joinColumns = @JoinColumn(
			name = "user_id",
			referencedColumnName = "id",
			nullable = true 
		),
		inverseJoinColumns = @JoinColumn(
			name = "authority_id",
			referencedColumnName = "id",
			nullable = true
		),
		uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "authority_id"})
	)
	private Collection<AuthorityEntity> authorities;
}
