package com.marsrovers.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Command {

	@NotNull(message = "Rover id cannot be null.")
	private Long roverId;
	
	@NotEmpty(message = "Instructions cannot be null nor empty.")
	@Pattern(regexp = "[LRM]+", message = "Instructions can only be L, R or M.")
	private String instructions;
}
