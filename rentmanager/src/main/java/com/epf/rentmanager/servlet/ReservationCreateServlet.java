package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Reservation_view;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Client> lesClients = ClientService.findAll();
            List<Vehicle> lesGovas = VehicleService.findAll();
            List<Reservation> lesResas = ReservationService.findAll();
            List<Reservation_view> lesResasView = new ArrayList<>();
            for (int i = 0; i < lesResas.size(); i++) {
                lesResasView.add(Reservation_view.get_Reservation(lesResas.get(i)));
            }
            request.setAttribute("lesResasView", lesResasView);
            request.setAttribute("lesClients", lesClients);
            request.setAttribute("lesGovas", lesGovas);
            request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
        }  catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            List<Reservation> lesResas = ReservationService.findAll();
            int taille = lesResas.size();

            String voiture = request.getParameter("car");
            int vehicle_id = 0;
            List<Vehicle> lesGovas = VehicleService.findAll();
             int tailleGovas = lesGovas.size();
            for (int i = 0; i < tailleGovas; i++) {
                String voitureComp = VehicleService.findById(lesGovas.get(i).getId()).getConstructeur()+" "+
                        VehicleService.findById(lesGovas.get(i).getId()).getModele();
                if (voitureComp.equalsIgnoreCase(voiture)) {
                    vehicle_id = lesGovas.get(i).getId();
                }
            }

            String client = request.getParameter("client");
            int client_id = 0;
            List<Client> lesClients = ClientService.findAll();
            int tailleClients = lesClients.size();
            for (int i = 0; i < tailleClients; i++) {
                String clientComp = ClientService.findById(lesClients.get(i).getId()).getPrenom()+" "+
                        ClientService.findById(lesClients.get(i).getId()).getNom();
                if (clientComp.equalsIgnoreCase(client)) {
                    client_id = lesClients.get(i).getId();
                }
            }

            String debut = request.getParameter("begin");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate Debut = LocalDate.parse(debut, formatter);
            String fin = request.getParameter("end");
            LocalDate Fin = LocalDate.parse(fin, formatter);
            ReservationService.create(new Reservation(ReservationService.findAll().get(taille-1).getId()+1, client_id, vehicle_id, Debut, Fin));
            response.sendRedirect(request.getContextPath()+"/rents");
        }  catch (ServiceException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
