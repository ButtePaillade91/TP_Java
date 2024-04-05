package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private static ClientDao clientDao;

	private ClientService(ClientDao clientDao){
		this.clientDao = clientDao;
	}

	public static long create(Client client) throws ServiceException {
		long intReturned = 0;
		if (!client.getPrenom().equalsIgnoreCase("") && !client.getNom().equalsIgnoreCase("")) {
			try {
				intReturned = clientDao.create(client);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		} else {
			throw new ServiceException("Le nom et le prénom du client ne peuvent pas être vides !");
		}
        return intReturned;
	}

	public static long delete(Client client) throws ServiceException {
		long intReturned = 0;
		if (client!=null && !client.getPrenom().equalsIgnoreCase("") && !client.getNom().equalsIgnoreCase("")) {
			try {
				intReturned = clientDao.delete(client);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		} else {
			throw new ServiceException("Le nom et le prénom du client ne peuvent pas être vides !");
		}
		return intReturned;
	}

	public static Client findById(long id) throws ServiceException {
		Client monClient = null;
		try {
			monClient = clientDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return monClient;
	}

	public static List<Client> findAll() throws ServiceException {
		List<Client> maListe = new ArrayList<>();
		try {
			maListe = clientDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return maListe;
	}

	public static int count() throws DaoException, ServiceException {
		int nbr = 0;
		try {
			nbr = clientDao.count();
			return nbr;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public static long set(Client client, String newLastName, String newFirstName, String newMail, String newBirthdate)
			throws ServiceException {
		long intReturned = 0;
		if (!newLastName.equalsIgnoreCase("") && !newFirstName.equalsIgnoreCase("")) {
            intReturned = ClientDao.set(client, newLastName, newFirstName, newMail, newBirthdate);
        } else {
			throw new ServiceException("Le nom et le prénom du client ne peuvent pas être vides !");
		}
		return intReturned;
	}
}
