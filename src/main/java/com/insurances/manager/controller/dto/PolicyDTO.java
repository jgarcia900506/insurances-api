package com.insurances.manager.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Positive;
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
public class PolicyDTO {

	private Long id;
	private Long clientId;
	private String type;
	private LocalDate effectiveStartDate;
	private LocalDate effectiveEndDate;
	
	@Positive(message = "Amount can't be zero or less than zero")
	private BigDecimal insuredAmount;
	
	private boolean status;

	@JsonIgnore
	@AssertTrue(message = "Effective start date can't be greater than  to effective end date")
    public boolean isValidDateRange() {
        if (effectiveStartDate == null || effectiveEndDate == null) {
            return true; // Null checks should be handled with @NotNull annotations if needed
        }

        return !effectiveStartDate.isAfter(effectiveEndDate);
    }

}
