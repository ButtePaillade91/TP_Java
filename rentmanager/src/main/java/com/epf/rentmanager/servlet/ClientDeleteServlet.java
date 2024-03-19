package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/users/delete")
public class ClientDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            String clientId = request.getParameter("clientId");
            if ("someAction".equals(action) && clientId != null && !clientId.isEmpty()) {
                Client client = ClientService.findById(Long.parseLong(clientId));
                if (client != null) {
                    ClientService.delete(client);
                    response.sendRedirect(request.getContextPath() + "/users");
                }
            }
        }  catch (ServiceException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la création du client : " + e.getMessage());
        }
    }
}