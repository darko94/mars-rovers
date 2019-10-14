package com.marsrovers.service.impl;

import org.springframework.stereotype.Service;

import com.marsrovers.exception.BadRequestException;
import com.marsrovers.model.Plateau;
import com.marsrovers.service.PlateauService;

@Service
public class PlateauServiceImpl implements PlateauService {

	/**
	 * Method that checks if given coordinates meets the conditions.
	 */
	@Override
	public void checkCoordinates(Integer xCoordinate, Integer yCoordinate) {
		Plateau plateau = Plateau.getInstance();
		if (plateau.getSizeX() == null || plateau.getSizeY() == null) {
			throw new BadRequestException("Plateau coordinates not set.");
		} else if (xCoordinate > plateau.getSizeX() || yCoordinate > plateau.getSizeY()) {
			throw new BadRequestException("Coordinates are not valid. They cannot be larger than plateau size.");
		}
	}

	/**
	 * Method which set the size of plateau. In case that size was already set, an
	 * exception is thrown.
	 */
	@Override
	public void setPlateauDimensions(Integer sizeX, Integer sizeY) {
		if (Plateau.getInstance().getSizeX() != null && Plateau.getInstance().getSizeY() != null) {
			throw new BadRequestException("Plateau coordinates already set. You cannot set it twice.");
		}
		Plateau.getInstance().setSizeX(sizeX);
		Plateau.getInstance().setSizeY(sizeY);
	}

}
