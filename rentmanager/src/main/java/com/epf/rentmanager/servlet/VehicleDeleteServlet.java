package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
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
import java.util.List;

@WebServlet("/cars/delete")
public class VehicleDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            String vehicleId = request.getParameter("vehicleId");
            if ("someAction".equals(action) && vehicleId != null && !vehicleId.isEmpty()) {
                Vehicle vehicle = VehicleService.findById(Long.parseLong(vehicleId));
                if (vehicle != null) {
                    List<Reservation> lesResasVoiture = ReservationService.findByVehicleId(vehicle.getId());
                    for (int i = 0; i < lesResasVoiture.size(); i++) {
                        ReservationService.delete(lesResasVoiture.get(i));
                    }
                    VehicleService.delete(vehicle);
                    response.sendRedirect(request.getContextPath() + "/cars");
                }
            }
        }  catch (ServiceException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la création du véhicule : " + e.getMessage());
        }
    }
}