package com.marsrovers.controller;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marsrovers.model.Rover;
import com.marsrovers.service.PlateauService;
import com.marsrovers.service.RoverService;

/**
 * Rest controller for working with @Rover model. Provides methods for getting
 * info about all the rovers or one specific rover, adding new rover or removing
 * an existing rover.
 * 
 * @author Darko.Misic
 *
 */
@RestController
@RequestMapping("/rovers")
@Validated
public class RoverController {

	@Autowired
	private RoverService roverService;

	@Autowired
	private PlateauService plateauService;

	/**
	 * An api method that provides info about all available rovers on plateau.
	 * 
	 * @return list of all the rovers available.
	 */
	@GetMapping
	public ResponseEntity<List<Rover>> getAllRovers() {
		return new ResponseEntity<>(roverService.getAllRovers(), HttpStatus.OK);
	}

	/**
	 * An api method that provides info about one specific rover, by given id.
	 * 
	 * @param roverId
	 * @return an object that represents rover with given id.
	 */
	@GetMapping("/{roverId}")
	public ResponseEntity<Rover> getRoverById(@PathVariable Long roverId) {
		return new ResponseEntity<>(roverService.getRoverById(roverId), HttpStatus.OK);
	}

	/**
	 * An api method for deploying new rover on plateau.
	 * 
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param direction
	 * @return id of newly added rover.
	 */
	@PostMapping
	public ResponseEntity<Long> deployRover(
			@NotNull(message = "X coordinate of the rover cannot be null.") @Min(value = 0, message = "Value of X coordinate cannot be less than 0.") @RequestParam Integer xCoordinate,
			@NotNull(message = "Y coordinate of the rover cannot be null.") @Min(value = 0, message = "Value of Y coordinate cannot be less than 0.") @RequestParam Integer yCoordinate,
			@NotEmpty(message = "Direction of the rover cannot be null nor empty.") @Pattern(regexp = "[NESW]", message = "Allowed values for direction are: N, E, S and W.") @RequestParam String direction) {
		plateauService.checkCoordinates(xCoordinate, yCoordinate);

		Rover rover = new Rover(xCoordinate, yCoordinate, direction.toCharArray()[0]);

		return new ResponseEntity<>(roverService.addRover(rover), HttpStatus.OK);
	}

	/**
	 * An api method for removing rover from the plateau.
	 * 
	 * @param roverId
	 * @return if the rover was successfully removed.
	 */
	@DeleteMapping("/{roverId}")
	public ResponseEntity<Boolean> deleteRover(@PathVariable Long roverId) {
		Rover rover = roverService.getRoverById(roverId);
		return new ResponseEntity<>(roverService.deleteRover(rover), HttpStatus.OK);
	}
}
