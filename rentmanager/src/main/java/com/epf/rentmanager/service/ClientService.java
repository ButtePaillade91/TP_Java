package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ServiceException;

public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;

	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}

	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}

		return instance;
	}


	public long create(Client client) throws ServiceException {
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

	public long delete(Client client) throws ServiceException {
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

	public Client findById(long id) throws ServiceException {
		Client monClient = null;
		try {
			monClient = clientDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return monClient;
	}

	public List<Client> findAll() throws ServiceException {
		List<Client> maListe = new ArrayList<>();
		try {
			maListe = clientDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return maListe;
	}

}
