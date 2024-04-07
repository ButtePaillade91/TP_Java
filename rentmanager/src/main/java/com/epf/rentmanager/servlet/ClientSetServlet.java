package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/users/set")
public class ClientSetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("getMails".equals(action)) {
            try {
                String clientId = request.getParameter("clientId");

                List<Client> lesClients = ClientService.findAll();
                List<String> lesMails = new ArrayList<>();
                for (int i = 0; i < lesClients.size(); i++) {
                    lesMails.add(lesClients.get(i).getEmail());
                }
                lesMails.add(ClientService.findById(Long.parseLong(clientId)).getEmail());
                String jsonMails = new Gson().toJson(lesMails);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonMails);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        } else {
            String clientId = request.getParameter("clientId");
            Client client = null;
            try {
                client = ClientService.findById(Long.parseLong(clientId));
                String formattedDate = client.getNaissance().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                request.setAttribute("formattedDate", formattedDate);
                request.setAttribute("clientId", clientId);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }

            request.setAttribute("client", client);

            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/set.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
        String clientId = request.getParameter("clientId");
        String newLastName = request.getParameter("last_name");
        String newFirstName = request.getParameter("first_name");
        String newMail = request.getParameter("email");
        String newBirthdate = request.getParameter("dateNaissance");
        Client client = ClientService.findById(Long.parseLong(clientId));
        ClientService.set(client, newLastName, newFirstName, newMail, newBirthdate);
        response.sendRedirect(request.getContextPath() + "/users");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}