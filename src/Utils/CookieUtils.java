package Utils;

import DAO.UserDAO;
import DTO.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

public class CookieUtils {
    public static String generateUid() {
        Random random = new Random();
        String validChars = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ1234567890";
        char[] text = new char[50];
        for (int i = 0; i < text.length; i++) {
            text[i] = validChars.charAt(random.nextInt(validChars.length()));
        }
        return new String(text);
    }

    public static void setCookie(HttpServletResponse response, User user) {
        UserDAO userDAO = new UserDAO();
        String uid = generateUid();
        Cookie cookie = new Cookie("uid", uid);
        response.addCookie(cookie);
        userDAO.updateCookie(user.getId(), uid);
    }

    public static void invalidateCookie(HttpServletRequest request) {
        String uid = extractUid(request);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByCookie(uid);
        if (user != null) {
            userDAO.updateCookie(user.getId(), null);
        }
    }

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
