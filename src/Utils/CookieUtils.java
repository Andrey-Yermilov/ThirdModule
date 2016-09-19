package Utils;

import DAO.UserDAO;
import DTO.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * utility class for operations with cookie
 */
public class CookieUtils {
    /**
     * generate new cookie uid
     * @return cookie
     */
    public static String generateUid() {
        Random random = new Random();
        String validChars = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ1234567890";
        char[] text = new char[50];
        for (int i = 0; i < text.length; i++) {
            text[i] = validChars.charAt(random.nextInt(validChars.length()));
        }
        return new String(text);
    }

    /**
     * method generates new cookie, updates DB with new cookie value and set cookie in response
     * @param response Http Servlet Response
     * @param user user
     */
    public static void setCookie(HttpServletResponse response, User user) {
        UserDAO userDAO = new UserDAO();
        String uid = generateUid();
        Cookie cookie = new Cookie("uid", uid);
        response.addCookie(cookie);
        userDAO.updateCookie(user.getId(), uid);
    }

    /**
     * method extracts cookie uid from request and delete this value from DB
     * @param request Http Servlet Request
     */
    public static void invalidateCookie(HttpServletRequest request) {
        String uid = extractUid(request);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByCookie(uid);
        if (user != null) {
            userDAO.updateCookie(user.getId(), null);
        }
    }

    /**
     * method extracts cookie uid from request
     * @param request Http Servlet Request
     * @return cookie uid
     */
    public static String extractUid(HttpServletRequest request) {
        String uid = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("uid")) {
                    uid = cookie.getValue();
                }
            }
        }
        return uid;
    }

    /**
     * method find user by cookie and if user exists returns his id
     * @param request Http Servlet Request
     * @return user's id
     */
    public static int checkUserByCookie(HttpServletRequest request) {
        int userId = 0;
        String uid = extractUid(request);
        if (uid != null) {
            User user = (new UserDAO()).findUserByCookie(uid);
            if (user != null) {
                userId = user.getId();
            }
        }
        return userId;
    }
}
