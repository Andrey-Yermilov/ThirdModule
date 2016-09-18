package Utils;

import DAO.UserDAO;
import DTO.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserChecker {

    public static String hashCode(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0-" + md5Hex;
        }

        return md5Hex;
    }

    public static boolean checkUser(String login, String pass) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(hashCode(pass)))
                return true;
            else return false;
        } else return false;
    }
}

