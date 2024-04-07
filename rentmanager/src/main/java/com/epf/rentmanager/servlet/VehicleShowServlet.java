package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Reservation_view;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import jdk.internal.net.http.frame.Http2Frame;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cars/show")
public class VehicleShowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vehicleId = request.getParameter("vehicleId");
        Vehicle vehicle = null;
        List<Reservation> resasVehicle = new ArrayList<>();
        try {
            vehicle = VehicleService.findById(Long.parseLong(vehicleId));
            resasVehicle = ReservationService.findByVehicleId(vehicle.getId());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        List<Reservation_view> resasVehicleView = new ArrayList<>();
        for (int i = 0; i < resasVehicle.size(); i++) {
            try {
                resasVehicleView.add(Reservation_view.get_Reservation(resasVehicle.get(i)));
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        request.setAttribute("vehicle", vehicle);
        request.setAttribute("resasVehicleView", resasVehicleView);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/show.jsp").forward(request, response);
    }
}
