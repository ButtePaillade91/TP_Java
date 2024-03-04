package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		long monGamos = 0;
		if (!vehicle.getConstructeur().equalsIgnoreCase("") && vehicle.getNb_places()>1) {
			try {
				monGamos = vehicleDao.create(vehicle);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		} else {
			if (vehicle.getConstructeur().equalsIgnoreCase("")) {
				throw new ServiceException("Votre véhicule doit avoir un constructeur !");
			} else if (vehicle.getNb_places()<=1) {
				throw new ServiceException("Votre véhicule doit avoir minimum 2 places !");
			}
		}
		return monGamos;
	}

	public long delete(Vehicle vehicle) throws ServiceException {
		long intReturned = 0;
		if (vehicle!=null && !vehicle.getConstructeur().equalsIgnoreCase("") && !vehicle.getModele().equalsIgnoreCase("")) {
			try {
				intReturned = vehicleDao.delete(vehicle);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		} else {
			throw new ServiceException("Le constructeur et le modèle du véhicule ne peuvent pas être vides !");
		}
		return intReturned;
	}

	public Vehicle findById(long id) throws ServiceException {
		Vehicle laGova = null;
		try {
			laGova = vehicleDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return laGova;
	}

	public List<Vehicle> findAll() throws ServiceException {
		List<Vehicle> liiiiiiiiiiist = new ArrayList<>();
		try {
			liiiiiiiiiiist = vehicleDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return liiiiiiiiiiist;
	}
}
