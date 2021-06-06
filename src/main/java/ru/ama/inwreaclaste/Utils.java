package ru.ama.inwreaclaste;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {

    public static void setSessionUser(HttpServletRequest request, User user ){
        HttpSession session = request.getSession();
        session.setAttribute(Constants.SESSION_USER, user);
    }

    public static User getSessionUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        return (User) session.getAttribute(Constants.SESSION_USER);
    }
}
