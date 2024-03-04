package com.epf.rentmanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;

import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	
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
	

}
