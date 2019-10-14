package com.marsrovers.dao;

import java.util.List;

import com.marsrovers.model.Rover;

public interface RoverDao {

	List<Rover> getAllRovers();
	Rover getRoverById(Long roverId);
	Long addRover(Rover rover);
	Boolean deleteRover(Rover rover);
}
