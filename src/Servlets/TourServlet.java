package Servlets;

import DAO.OrderDAO;
import DAO.TourDAO;
import DTO.Order;
import DTO.Tour;
import Utils.CookieUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TourServlet extends HttpServlet {
    TourDAO tourDAO;
    OrderDAO orderDAO;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        tourDAO = new TourDAO();
        orderDAO = new OrderDAO();
        int userId = CookieUtils.checkUserByCookie(request);
        if (userId != 0) {
            ServletContext context = getServletContext();
            List<Tour> tours = tourDAO.findAll();
            List<Order> orders = orderDAO.findAllByUserId(userId);
            context.setAttribute("orders", orders);
            context.setAttribute("tours", tours);
            request.getRequestDispatcher("jsp/tours.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "index.jsp");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        tourDAO.close();
        orderDAO.close();
    }
}
