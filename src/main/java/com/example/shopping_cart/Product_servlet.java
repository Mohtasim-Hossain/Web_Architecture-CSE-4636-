package com.example.shopping_cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Product_servlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * This method is responsible for displaying product list to the current user
     * It has a hardcoded static list of products and generates
     * HTML to display the product information to the user.
     * @param request the request object that carries the client information
     * @param response the response object that will be sent to the client
     **/
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = false;
        Cookie [] cookieArray = request.getCookies();
        PrintWriter out = response.getWriter();

        out.println("<html>\n" +
                "<head><title>Product List</title></head>\n" +
                "<body>\n");

        for (Cookie c:cookieArray) {
            if (c.getName().equals("usernameKey")) {
                flag = true;
                out.print("<center><span><h3> Username: " + c.getValue() + " </h3>");
                break;
            }
        }
        if (!flag) {
            out.println("<h1> Invalid session. Please log in again. " +
                    "</h1>");
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            rd.include(request, response);
        }


        HttpSession session = request.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        out.println("<h1 align = \"center\">Product List </h1>\n" +
                "<table border = \"3\" align = \"center\">\n" +
                "<tr>\n" +
                "<th>Fruit Name</th><th>Price/Kg</th><th>Add to Cart</th>" +
                "</tr>");

        List<Product_details> product_list = new ArrayList<Product_details>();

        product_list.add(new Product_details("Apple", 150, 0));
        product_list.add(new Product_details("Orange", 130, 0));
        product_list.add(new Product_details("Strawberry", 300, 0));
        product_list.add(new Product_details("Grape", 200, 0));
        product_list.add(new Product_details("Pear", 180, 0));
        product_list.add(new Product_details("Mango", 120, 0));
        product_list.add(new Product_details("Papaya", 120, 0));
        product_list.add(new Product_details("Blueberry", 600, 0));

        for (Product_details product : product_list) {
            out.println("<tr>" +
                    "<td>" + product.get_Name() + "</td>" +
                    "<td>" + product.get_Price() + "</td>" +
                    "<td>" +
                    "<form action='cart' method='post'>" +
                    "<input type='hidden' name='name' value='" + product.get_Name() + "'>" +
                    "<input type='hidden' name='price' value='" + product.get_Price() + "'>" +
                    "<input type='hidden' name='action' value='add'>" +
                    "<input type='submit' value='Select'>" +
                    "</form>" +
                    "</td>" +
                    "</tr>");
        }

        out.println("<center><form method='post' action='cart'>" +
                "<input type='hidden' name='action' value='view'>" +
                "<input type='submit' value='Go to Cart'>" +
                "</form></center>");

        out.println("<center><form method='post' action='logout'>" +
                "<input type='hidden' name='action' value='view'>" +
                "<input type='submit' value='Log out'>" +
                "</form></center>");



        out.println("</table>" +
                "</body>" +
                "</html>");
    }
}
