package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClientService.getInstance();
		VehicleService.getInstance();
		ReservationService.getInstance();
        try {
			int nbrClients = ClientService.instance.count();
            int nbrVehicles = VehicleService.instance.count();
			int nbrResas = ReservationService.instance.count();
			request.setAttribute("nbrClients", nbrClients);
			request.setAttribute("nbrVehicles", nbrVehicles);
			request.setAttribute("nbrResas", nbrResas);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        } catch (ServiceException e) {
			throw new RuntimeException(e);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

}
