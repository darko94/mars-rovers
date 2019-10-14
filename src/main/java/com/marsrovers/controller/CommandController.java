package com.marsrovers.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marsrovers.model.Command;
import com.marsrovers.service.CommandService;

/**
 * Rest controller for working with @Command model. Provides method for
 * executing the command on given rover.
 * 
 * @author Darko.Misic
 *
 */
@RestController
@RequestMapping("/command")
public class CommandController {

	@Autowired
	private CommandService commandService;

	/**
	 * An api method that executes given command on the rover with given id.
	 * 
	 * @param command
	 * @return info about rover on which is command executed.
	 */
	@PostMapping
	public ResponseEntity<String> executeCommand(@Valid @RequestBody Command command) {
		return new ResponseEntity<String>(commandService.executeCommand(command), HttpStatus.OK);
	}
}
