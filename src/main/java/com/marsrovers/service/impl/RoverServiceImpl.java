package com.marsrovers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marsrovers.dao.RoverDao;
import com.marsrovers.model.Rover;
import com.marsrovers.service.RoverService;

@Service
public class RoverServiceImpl implements RoverService {

	@Autowired
	private RoverDao roverDao;
	
	@Override
	public List<Rover> getAllRovers() {
		return roverDao.getAllRovers();
	}

	@Override
	public Rover getRoverById(Long roverId) {
		return roverDao.getRoverById(roverId);
	}

	@Override
	public Long addRover(Rover rover) {
		return roverDao.addRover(rover);
	}

	@Override
	public Boolean deleteRover(Rover rover) {
		return roverDao.deleteRover(rover);
	}

}
