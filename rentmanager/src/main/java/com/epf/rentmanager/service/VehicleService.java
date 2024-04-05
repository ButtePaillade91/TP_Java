package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private static VehicleDao vehicleDao;

	private VehicleService(VehicleDao vehicleDao){
		VehicleService.vehicleDao = vehicleDao;
	}
	
	public static long create(Vehicle vehicle) throws ServiceException {
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

	public static long delete(Vehicle vehicle) throws ServiceException {
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

	public static Vehicle findById(long id) throws ServiceException {
		Vehicle laGova = null;
		try {
			laGova = vehicleDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return laGova;
	}

	public static List<Vehicle> findAll() throws ServiceException {
		List<Vehicle> liiiiiiiiiiist = new ArrayList<>();
		try {
			liiiiiiiiiiist = vehicleDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return liiiiiiiiiiist;
	}

	public static int count() throws DaoException, ServiceException {
		int nbr = 0;
		try {
			nbr = vehicleDao.count();
			return nbr;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public static long set(Vehicle vehicle, String newMarque, String newModele, int newNbPlaces)
			throws ServiceException {
		long intReturned = 0;
		if (!newMarque.equalsIgnoreCase("") && !newModele.equalsIgnoreCase("")) {
			intReturned = VehicleDao.set(vehicle, newMarque, newModele, newNbPlaces);
		} else {
			throw new ServiceException("Le nom et le prénom du véhicule ne peuvent pas être vides !");
		}
		return intReturned;
	}
}
