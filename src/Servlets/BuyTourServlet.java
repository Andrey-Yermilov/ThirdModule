package Servlets;

import DAO.OrderDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet which handles buy action
 */
public class BuyTourServlet extends HttpServlet {
    OrderDAO orderDAO = new OrderDAO();

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
     * get tour's id and user's id, update DB and reload tours page
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        int tourId = 0;
        int userId = 0;
        ServletContext context = getServletContext();
        try {
            tourId = Integer.parseInt(request.getParameter("tourId"));
            userId = (int) context.getAttribute("userId");
        } catch (NumberFormatException e) {
        }
        if (tourId > 0 && userId > 0) {
            orderDAO.create(tourId, userId);
        }
        request.getRequestDispatcher(request.getContextPath() + "/tours.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        orderDAO.close();
    }
}
