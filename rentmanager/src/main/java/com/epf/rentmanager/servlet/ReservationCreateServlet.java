package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Reservation_view;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;

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
        ReservationService.getInstance();
        ClientService.getInstance();
        try {
            List<Client> lesClients = ClientService.instance.findAll();
            List<Reservation> lesResas = ReservationService.instance.findAll();
            List<Reservation_view> lesResasView = new ArrayList<>();
            for (int i = 0; i < lesResas.size(); i++) {
                lesResasView.add(Reservation_view.get_Reservation(lesResas.get(i)));
            }
            request.setAttribute("lesResasView", lesResasView);
            request.setAttribute("lesClients", lesClients);
            request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
        }  catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        ReservationService.getInstance();
        try {
            List<Reservation> lesResas = ReservationService.instance.findAll();
            int taille = lesResas.size();

            String client = request.getParameter("client");
            String prenom  = client.split(" ")[0];
            String nom  = client.split(" ")[1];
            for (int i = 0; i < taille; i++) {
                System.out.println(lesResas.get(i));
            }

            String marque = request.getParameter("first_name");
            String mail = request.getParameter("email");
            String naissance = request.getParameter("dateNaissance");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateNaissance = LocalDate.parse(naissance, formatter);
            //ReservationService.instance.create(new Reservation(taille+1);
            response.sendRedirect(request.getContextPath()+"/rents");
        }  catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
