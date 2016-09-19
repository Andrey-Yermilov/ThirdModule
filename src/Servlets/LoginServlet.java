package Servlets;

import DAO.UserDAO;
import DTO.User;
import Utils.CookieUtils;
import Utils.UserChecker;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet which handles login action
 */
public class LoginServlet extends HttpServlet {
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
     * get user's login and password and redirect to tour page if they are correct
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        userDAO = new UserDAO();
        ServletContext context = getServletContext();
        if (UserChecker.checkUser(login, password)) {
            User user = userDAO.findUserByLogin(login);
            CookieUtils.setCookie(response, user);
            context.setAttribute("name", user.getName());
            context.setAttribute("userId", user.getId());
            context.setAttribute("errorLoginPassMessage", null);
            response.sendRedirect(request.getContextPath() + "/tours.jsp");
        } else {
            context.setAttribute("errorLoginPassMessage", "Неверный логин или пароль!");
            response.sendRedirect(request.getContextPath() + "index.jsp");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        userDAO.close();
    }
}
