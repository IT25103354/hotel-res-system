package com.hotel.reservation.controller;

import com.hotel.reservation.model.User;
import jakarta.servlet.http.HttpSession;

public class SessionHelper {

    public static final String USER_SESSION_KEY = "user";

    public static User getLoggedInUser(HttpSession session) {
        if (session == null) {
            return null;
        }
        Object value = session.getAttribute(USER_SESSION_KEY);
        if (value instanceof User) {
            return (User) value;
        }
        return null;
    }

    public static boolean isLoggedIn(HttpSession session) {
        return getLoggedInUser(session) != null;
    }
}
