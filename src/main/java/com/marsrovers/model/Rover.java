package com.marsrovers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rover {

	private Long id;
	private Integer xCoordinate;
	private Integer yCoordinate;
	private Character direction;

	public Rover(Integer xCoordinate, Integer yCoordinate, Character direction) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.direction = direction;
	}
}
