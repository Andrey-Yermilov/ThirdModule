package Servlets;

import DAO.OrderDAO;
import DAO.TourDAO;
import DAO.UserDAO;
import DTO.Order;
import DTO.Tour;
import DTO.User;
import Utils.CookieUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * servlet which handles action with tours page
 */
public class TourServlet extends HttpServlet {
    TourDAO tourDAO;
    OrderDAO orderDAO;
    UserDAO userDAO;

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

    /**
     * check user by cookie and if cookie are correct display all available tours and all user's tours
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        tourDAO = new TourDAO();
        orderDAO = new OrderDAO();
        int userId = CookieUtils.checkUserByCookie(request);
        if (userId != 0) {
            userDAO = new UserDAO();
            User user = userDAO.findEntityById(userId);
            ServletContext context = getServletContext();
            context.setAttribute("name", user.getName());
            context.setAttribute("userId", user.getId());
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
        userDAO.close();
    }
}
