package com.marsrovers.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marsrovers.model.Plateau;
import com.marsrovers.service.PlateauService;

/**
 * Rest controller for working with @Plateau model. Provides methods for setting
 * plateau dimensions and getting info about plateau.
 * 
 * @author Darko.Misic
 *
 */
@RestController
@RequestMapping("/plateau")
@Validated
public class PlateauController {

	@Autowired
	private PlateauService plateauService;

	@PostMapping
	public ResponseEntity<Void> setPlateauDimensions(
			@NotNull(message = "Size X of Plateau cannot be null.") @Min(value = 0, message = "Size X of plateau cannot be negative.") @RequestParam("sizeX") Integer sizeX,
			@NotNull(message = "Size Y of Plateau cannot be null.") @Min(value = 0, message = "Size Y of plateau cannot be negative.") @RequestParam("sizeY") Integer sizeY) {
		plateauService.setPlateauDimensions(sizeX, sizeY);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Getting info about plateau size.
	 * 
	 * @return plateau object.
	 */
	@GetMapping("/info")
	public ResponseEntity<Plateau> getPlateauInfo() {
		return new ResponseEntity<>(Plateau.getInstance(), HttpStatus.OK);
	}
}
