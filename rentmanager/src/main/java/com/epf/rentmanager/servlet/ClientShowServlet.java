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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/show")
public class ClientShowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientId = request.getParameter("clientId");
        Client client = null;
        List<Reservation> resasClient = new ArrayList<>();
        try {
            client = ClientService.findById(Long.parseLong(clientId));
            resasClient = ReservationService.findByClientId(client.getId());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        List<Reservation_view> resasClientView = new ArrayList<>();
        for (int i = 0; i < resasClient.size(); i++) {
            try {
                resasClientView.add(Reservation_view.get_Reservation(resasClient.get(i)));
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        request.setAttribute("client", client);
        request.setAttribute("resasClientView", resasClientView);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/show.jsp").forward(request, response);
    }
}
