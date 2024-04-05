package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

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
import java.util.Date;

@WebServlet("/users/set")
public class ClientSetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientId = request.getParameter("clientId");
        Client client = null;
        try {
            client = ClientService.findById(Long.parseLong(clientId));
            String formattedDate = client.getNaissance().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            request.setAttribute("formattedDate", formattedDate);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("client", client);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/set.jsp").forward(request, response);
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