package com.example.shopping_cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Logout_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * This method is responsible for handling user logout requests. It invalidates
     * the user's current session, and then redirect the user to the login page
     * @param request the request object that carries the client information
     * @param response the response object that will be sent to the client
     **/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie [] cookieArray = request.getCookies();
        Cookie temp = null;
        for (Cookie ck:cookieArray)
        {
            if (ck.getName().equals("usernameKey"))
                temp = ck;
        }

        if (temp!=null)
        {
            temp.setMaxAge(0);
        }
        PrintWriter out = response.getWriter();
        out.println("<h1> Successfully logged out </h1>");

        RequestDispatcher rd = request.getRequestDispatcher("login.html");
        rd.include(request, response);
    }
}