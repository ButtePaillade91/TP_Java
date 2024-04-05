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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet("/cars/set")
public class VehicleSetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vehicleId = request.getParameter("vehicleId");
        Vehicle vehicle = null;
        try {
            vehicle = VehicleService.findById(Long.parseLong(vehicleId));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("vehicle", vehicle);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/set.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            String vehicleId = request.getParameter("vehicleId");
            String newMarque = request.getParameter("manufacturer");
            String newModele = request.getParameter("modele");
            String newNbPlaces = request.getParameter("seats");
            Vehicle vehicle = VehicleService.findById(Long.parseLong(vehicleId));
            VehicleService.set(vehicle, newMarque, newModele, Integer.parseInt(newNbPlaces));
            response.sendRedirect(request.getContextPath() + "/cars");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
