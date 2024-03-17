package com.epf.rentmanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {

	private ReservationDao() {}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_RESERVATION_QUERY);
			preparedStatement.setInt(1, reservation.getClient_id());
			preparedStatement.setInt(2, reservation.getVehicle_id());
			Date debutConv = Date.valueOf(reservation.getDebut());
			Date finConv = Date.valueOf(reservation.getFin());
			preparedStatement.setDate(3, debutConv);
			preparedStatement.setDate(4, finConv);
			preparedStatement.execute();
			return 1;
		} catch (SQLException error) {
			System.out.println(error);
			return 0;
		}
	}
	
	public static long delete(Reservation reservation) throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_RESERVATION_QUERY);
			preparedStatement.setInt(1, reservation.getId());
			preparedStatement.execute();
			return 1;
		} catch (SQLException error) {
			return 0;
		}
	}

	public static Reservation findById(long id) throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(FIND_RESERVATION_QUERY);
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			ResultSet result = preparedStatement.getResultSet();
			if (result.next()) {
				return new Reservation(result.getInt("id"), result.getInt("client_id"), result.getInt
						("vehicle_id"), result.getDate("debut").toLocalDate(),
						result.getDate("fin").toLocalDate());
			} else {
				System.out.println("Aucun véhicule ne possède cet identifiant !");
				return null;
			}
		} catch (SQLException error) {
			System.out.println(error);
			return null;
		}
	}
	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			preparedStatement.setLong(1, clientId);
			preparedStatement.execute();

			ArrayList<Reservation> listeResa = new ArrayList<>();
			ResultSet result = preparedStatement.getResultSet();
			while (result.next()) {
				listeResa.add(new Reservation(result.getInt("id"), Math.toIntExact(clientId),
						result.getInt("vehicle_id"), result.getDate("debut").toLocalDate(),
						result.getDate("fin").toLocalDate()));
			}
			return listeResa;
		} catch (SQLException error) {
			System.out.println(error);
			return null;
		}
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			preparedStatement.setLong(1, vehicleId);
			preparedStatement.execute();

			ArrayList<Reservation> listeResa = new ArrayList<>();
			ResultSet result = preparedStatement.getResultSet();
			while (result.next()) {
				listeResa.add(new Reservation(result.getInt("id"), result.getInt("client_id"),
						Math.toIntExact(vehicleId), result.getDate("debut").toLocalDate(),
						result.getDate("fin").toLocalDate()));
			}
			return listeResa;
		} catch (SQLException error) {
			return null;
		}
	}

	public List<Reservation> findAll() throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(FIND_RESERVATIONS_QUERY);
			preparedStatement.execute();

			ArrayList<Reservation> maListe = new ArrayList<>();
			ResultSet result = preparedStatement.getResultSet();
			while (result.next()) {
				maListe.add(new Reservation(result.getInt("id"), result.getInt("client_id"), result.getInt("vehicle_id"), result.getDate("debut").toLocalDate(), result.getDate("fin").toLocalDate()));
			}
			return maListe;
		} catch (SQLException error) {
			return null;
		}
	}

	public int count() throws DaoException {
		List<Reservation> nbr = findAll();
		return nbr.size();
	}
}
