package com.marsrovers.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.marsrovers.dao.RoverDao;
import com.marsrovers.exception.NotFoundException;
import com.marsrovers.model.Rover;

@Repository
public class RoverDaoImpl implements RoverDao {

	private final List<Rover> rovers = new ArrayList<>();
	private Long roverCount = 1L;

	@Override
	public List<Rover> getAllRovers() {
		return rovers;
	}

	@Override
	public Rover getRoverById(Long roverId) {
		return rovers.stream().filter(rover -> rover.getId().equals(roverId)).findAny()
				.orElseThrow(() -> new NotFoundException("Rover with given id not found."));
	}

	@Override
	public Long addRover(Rover rover) {
		rover.setId(roverCount++);
		rovers.add(rover);
		return rover.getId();
	}

	@Override
	public Boolean deleteRover(Rover rover) {
		return rovers.removeIf(r -> r.equals(rover));
	}

}
