package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Reservation_view;
import com.epf.rentmanager.model.Vehicle;
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

@WebServlet("/rents")
public class ReservationListServlet extends HttpServlet  {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReservationService.getInstance();
        try {
            List<Reservation> lesResas = ReservationService.instance.findAll();
            List<Reservation_view> lesResasView = new ArrayList<>();
            for (int i = 0; i < lesResas.size(); i++) {
                lesResasView.add(Reservation_view.get_Reservation(lesResas.get(i)));
            }
            request.setAttribute("lesResasView", lesResasView);
            request.getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
        }  catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
