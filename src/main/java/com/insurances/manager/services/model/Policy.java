package com.insurances.manager.services.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

	private Long id;
	private PolicyTypes type;
	private LocalDate effectiveStartDate;
	private LocalDate effectiveEndDate;
	private BigDecimal insuredAmount;
	private boolean status;

}
