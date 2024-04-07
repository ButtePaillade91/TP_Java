package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import com.google.gson.Gson;

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


@WebServlet("/users/create")
public class ClientCreateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("getMails".equals(action)) {
            try {
                List<Client> lesClients = ClientService.findAll();
                List<String> lesMails = new ArrayList<>();
                for (int i = 0; i < lesClients.size(); i++) {
                    lesMails.add(lesClients.get(i).getEmail());
                }

                String jsonMails = new Gson().toJson(lesMails);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonMails);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                List<Client> lesClients = ClientService.findAll();
                request.setAttribute("clients", lesClients);

                this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            List<Client> lesClients = ClientService.findAll();
            int taille = lesClients.size();
            String nom = request.getParameter("last_name");
            String prenom = request.getParameter("first_name");
            String mail = request.getParameter("email");
            String naissance = request.getParameter("dateNaissance");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateNaissance = LocalDate.parse(naissance, formatter);
            ClientService.create(new Client(taille+1, nom, prenom, mail, dateNaissance));
            response.sendRedirect(request.getContextPath()+"/users");
        }  catch (ServiceException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la création du client : " + e.getMessage());
        }
    }
}