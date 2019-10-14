package com.marsrovers.service;

import java.util.List;

import com.marsrovers.model.Rover;

public interface RoverService {

	List<Rover> getAllRovers();
	Rover getRoverById(Long roverId);
	Long addRover(Rover rover);
	Boolean deleteRover(Rover rover);
}
