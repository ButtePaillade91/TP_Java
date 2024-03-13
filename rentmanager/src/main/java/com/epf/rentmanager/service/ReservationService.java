package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }


    public long create(Reservation reservation) throws ServiceException {
        long maResa = 0;
        try {
            maResa = reservationDao.create(reservation);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return maResa;
    }

    public long delete(Reservation reservation) throws ServiceException {
        long intReturned = 0;
        if (reservation!=null && reservation.getDebut()!=null && reservation.getFin()!=null) {
            try {
                intReturned = ReservationDao.delete(reservation);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("La date de début et la date de fin ne peuvent pas être vides !");
        }
        return intReturned;
    }

    public Reservation findById(long id) throws ServiceException {
        Reservation laResa = null;
        try {
            laResa = ReservationDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return laResa;
    }

    public List<Reservation> findByClientId(long clientid) throws ServiceException {
        List<Reservation> listeResa = new ArrayList<>();
        try {
            listeResa = reservationDao.findResaByClientId(clientid);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return listeResa;
    }

    public List<Reservation> findByVehicleId(long vehicleid) throws ServiceException {
        List<Reservation> listeResa = new ArrayList<>();
        try {
            listeResa = reservationDao.findResaByVehicleId(vehicleid);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return listeResa;
    }

    public List<Reservation> findAll() throws ServiceException {
        List<Reservation> liiiiiiiiiiist = new ArrayList<>();
        try {
            liiiiiiiiiiist = reservationDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return liiiiiiiiiiist;
    }

    public int count() throws DaoException, ServiceException {
        int nbr = 0;
        try {
            nbr = reservationDao.count();
            return nbr;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
