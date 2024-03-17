package com.epf.rentmanager.model;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;

public class Reservation_view {
    private int id;
    private Vehicle vehicle;
    private Client client;
    private LocalDate Debut;
    private LocalDate Fin;

    public Reservation_view(int id, Vehicle vehicle, Client client, LocalDate Debut, LocalDate Fin) {
        this.id = id;
        this.vehicle = vehicle;
        this.client = client;
        this.Debut = Debut;
        this.Fin = Fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDebut() {
        return Debut;
    }

    public void setDebut(LocalDate debut) {
        this.Debut = debut;
    }

    public LocalDate getFin() {
        return Fin;
    }

    public void setFin(LocalDate fin) {
        this.Fin = fin;
    }

    public static Reservation_view get_Reservation(Reservation resa) throws ServiceException {
        Reservation_view resaView = new Reservation_view(resa.getId(), null, null, resa.getDebut(), resa.getFin());
        resaView.vehicle = VehicleService.findById(resa.getVehicle_id());
        resaView.client = ClientService.findById(resa.getClient_id());
        return resaView;
    }
}
