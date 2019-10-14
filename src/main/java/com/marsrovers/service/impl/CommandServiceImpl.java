package com.marsrovers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marsrovers.exception.BadRequestException;
import com.marsrovers.model.Command;
import com.marsrovers.model.Plateau;
import com.marsrovers.model.Rover;
import com.marsrovers.service.CommandService;
import com.marsrovers.service.RoverService;

@Service
public class CommandServiceImpl implements CommandService {

	@Autowired
	private RoverService roverService;

	/**
	 * Executing the given command. In case that command is not valid, an exception
	 * is thrown and no rover is changed.
	 */
	@Override
	public String executeCommand(Command command) {
		Rover rover = roverService.getRoverById(command.getRoverId());

		Integer xCoordinate = rover.getXCoordinate();
		Integer yCoordinate = rover.getYCoordinate();
		Character direction = rover.getDirection();

		try {
			String[] instructions = command.getInstructions().split("");
			for (String instruction : instructions) {
				switch (instruction) {
				case "L":
					turnLeft(rover);
					break;
				case "R":
					turnRight(rover);
					break;
				case "M":
					moveForward(rover);
					break;
				}
			}
		} catch (BadRequestException ex) {
			rover.setXCoordinate(xCoordinate);
			rover.setYCoordinate(yCoordinate);
			rover.setDirection(direction);
			throw new BadRequestException("Wrong command. Given position is out of the plateau.");
		}

		return rover.getXCoordinate() + " " + rover.getYCoordinate() + " " + rover.getDirection();
	}

	/**
	 * Moves forward the rover by one point on plateau, in the current direction.
	 * 
	 * @param rover
	 */
	private void moveForward(Rover rover) {
		switch (rover.getDirection()) {
		case 'N':
			if (Plateau.getInstance().getSizeY() < rover.getYCoordinate() + 1) {
				throw new BadRequestException("Wrong command. Given position is out of the plateau.");
			} else {
				rover.setYCoordinate(rover.getYCoordinate() + 1);
			}
			break;
		case 'S':
			if (rover.getYCoordinate() < 1) {
				throw new BadRequestException("Wrong command. Given position is out of the plateau.");
			} else {
				rover.setYCoordinate(rover.getYCoordinate() - 1);
			}
			break;
		case 'W':
			if (rover.getXCoordinate() < 1) {
				throw new BadRequestException("Wrong command. Given position is out of the plateau.");
			} else {
				rover.setXCoordinate(rover.getXCoordinate() - 1);
			}
			break;
		case 'E':
			if (Plateau.getInstance().getSizeX() < rover.getXCoordinate() + 1) {
				throw new BadRequestException("Wrong command. Given position is out of the plateau.");
			} else {
				rover.setXCoordinate(rover.getXCoordinate() + 1);
			}
		}

	}

	/**
	 * Set rover direction for 90 degrees left.
	 * 
	 * @param rover
	 */
	private void turnLeft(Rover rover) {
		switch (rover.getDirection()) {
		case 'N':
			rover.setDirection('W');
			break;
		case 'W':
			rover.setDirection('S');
			break;
		case 'S':
			rover.setDirection('E');
			break;
		case 'E':
			rover.setDirection('N');
			break;
		}
	}

	/**
	 * Set rover direction for 90 degrees right.
	 * 
	 * @param rover
	 */
	private void turnRight(Rover rover) {
		switch (rover.getDirection()) {
		case 'N':
			rover.setDirection('E');
			break;
		case 'W':
			rover.setDirection('N');
			break;
		case 'S':
			rover.setDirection('W');
			break;
		case 'E':
			rover.setDirection('S');
			break;
		}
	}

}
