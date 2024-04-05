package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;

import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {

	private VehicleDao() {}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	private static final String SET_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur=?, modele=?, nb_places=? WHERE id=?;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_VEHICLE_QUERY);
			preparedStatement.setString(1, vehicle.getConstructeur());
			preparedStatement.setString(2, vehicle.getModele());
			preparedStatement.setInt(3, vehicle.getNb_places());
			preparedStatement.execute();
			return 1;
		} catch (SQLException error) {
			return 0;
		}
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_VEHICLE_QUERY);
			preparedStatement.setInt(1, vehicle.getId());
			preparedStatement.execute();
			return 1;
		} catch (SQLException error) {
			return 0;
		}
	}

	public Vehicle findById(long id) throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(FIND_VEHICLE_QUERY);
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			ResultSet result = preparedStatement.getResultSet();
			if (result.next()) {
				return new Vehicle(result.getInt("id"), result.getString("constructeur"), result.getString
						("modele"), result.getInt("nb_places"));
			} else {
				System.out.println("Aucun véhicule ne possède cet identifiant !");
				return null;
			}
		} catch (SQLException error) {
			return null;
		}
	}

	public List<Vehicle> findAll() throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(FIND_VEHICLES_QUERY);
			preparedStatement.execute();

			ArrayList<Vehicle> maListe = new ArrayList<>();
			ResultSet result = preparedStatement.getResultSet();
			while (result.next()) {
				maListe.add(new Vehicle(result.getInt("id"), result.getString("constructeur"), result.getString("modele"), result.getInt("nb_places")));
			}
			return maListe;
		} catch (SQLException error) {
			return null;
		}
	}
	
	public int count() throws DaoException {
		List<Vehicle> nbr = findAll();
		return nbr.size();
	}

	public static long set(Vehicle vehicle, String newMarque, String newModele, int newNbPlaces)
			throws ServiceException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(SET_VEHICLE_QUERY);
			if (vehicle.getConstructeur().equalsIgnoreCase(newMarque)) {
				preparedStatement.setString(1, vehicle.getConstructeur().toUpperCase());
			} else {
				preparedStatement.setString(1, newMarque.toUpperCase());
			}
			if (vehicle.getModele().equalsIgnoreCase(newModele)) {
				preparedStatement.setString(2, vehicle.getModele());
			} else {
				preparedStatement.setString(2, newModele);
			}
			if (vehicle.getNb_places() == newNbPlaces) {
				preparedStatement.setString(3, String.valueOf(vehicle.getNb_places()));
			} else {
				preparedStatement.setString(3, String.valueOf(newNbPlaces));
			}
			preparedStatement.setInt(4, vehicle.getId());
			preparedStatement.execute();
			return 1;
		} catch (SQLException error) {
			System.out.println(error);
			return 0;
		}
	}
}
