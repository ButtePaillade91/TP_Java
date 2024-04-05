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
public class ClientDao {

	private ClientDao() {}

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";

	private static final String SET_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";

	public long create(Client client) throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_CLIENT_QUERY);
			preparedStatement.setString(1, client.getNom().toUpperCase());
			preparedStatement.setString(2, client.getPrenom());
			preparedStatement.setString(3, client.getEmail());

			Date dateConvertie = Date.valueOf(client.getNaissance());
			preparedStatement.setDate(4, dateConvertie);

			preparedStatement.execute();
			return 1;
		} catch (SQLException error) {
			System.out.println(error);
			return 0;
		}
	}
	
	public long delete(Client client) throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_CLIENT_QUERY);
			preparedStatement.setInt(1, client.getId());
			preparedStatement.execute();
			return 1;
		} catch (SQLException error) {
			return 0;
		}
	}

	public Client findById(long id) throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(FIND_CLIENT_QUERY);
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			ResultSet result = preparedStatement.getResultSet();
			if(result.next()) {
				return new Client(Math.toIntExact(id), result.getString("nom"), result.getString
						("prenom"), result.getString("email"), result.getDate
						("naissance").toLocalDate());
			} else {
				System.out.println("Aucun client ne possède cet identifiant !");
				return null;
			}
		} catch (SQLException error) {
			System.out.println(error);
			return null;
		}
	}

	public List<Client> findAll() throws DaoException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(FIND_CLIENTS_QUERY);
			ResultSet result = preparedStatement.executeQuery();

			ArrayList<Client> maListe = new ArrayList<>();
			while (result.next()) {
				maListe.add(new Client(
						result.getInt("id"),
						result.getString("nom"),
						result.getString("prenom"),
						result.getString("email"),
						result.getDate("naissance").toLocalDate()
				));
			}
			return maListe;
		} catch (SQLException error) {
			return null;
		}
	}

	public int count() throws DaoException {
		List<Client> nbr = findAll();
		return nbr.size();
	}

	public static long set(Client client, String newLastName, String newFirstName, String newMail, String newBirthdate)
			throws ServiceException {
		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement(SET_CLIENT_QUERY);
			if (client.getNom().equalsIgnoreCase(newLastName)) {
				preparedStatement.setString(1, client.getNom().toUpperCase());
			}
			else {
				preparedStatement.setString(1, newLastName.toUpperCase());
			}
			if (client.getPrenom().equalsIgnoreCase(newFirstName)) {
				preparedStatement.setString(2, client.getPrenom());
			}
			else {
				preparedStatement.setString(2, newFirstName);
			}
			if (client.getEmail().equalsIgnoreCase(newMail)) {
				preparedStatement.setString(3, client.getEmail());
			}
			else {
				preparedStatement.setString(3, newMail);
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dateConvertie = LocalDate.parse(newBirthdate, formatter);
			if (client.getNaissance().equals(dateConvertie)) {
				preparedStatement.setDate(4, Date.valueOf(client.getNaissance()));
			}
			else {
				preparedStatement.setDate(4, Date.valueOf(dateConvertie));
			}
			preparedStatement.setInt(5, client.getId());
			preparedStatement.execute();
			return 1;
		} catch (SQLException error) {
			System.out.println(error);
			return 0;
		}
	}
}
