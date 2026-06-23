package com.contactmanager.contactmanager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/contacts")
public class AddContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        if(name == null || name.trim().isEmpty()){

            response.setContentType("text/html");
            response.getWriter().println(
                    "<h2 style='color:red;text-align:center;'>❌ Name is Required</h2>" +
                            "<div style='text-align:center;'>" +
                            "<a href='contact-form.jsp'>Go Back</a>" +
                            "</div>"
            );

            return;
        }
        if(email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            response.setContentType("text/html");
            response.getWriter().println(
                    "<h2 style='color:red;text-align:center;'>❌ Invalid Email Address</h2>" +
                            "<div style='text-align:center;'>" +
                            "<a href='contact-form.jsp'>Go Back</a>" +
                            "</div>"
            );
            return;
        }
        if(phone == null || !phone.matches("\\d{10}")){
            response.setContentType("text/html");
            response.getWriter().println(
                    "<h2 style='color:red;text-align:center;'>❌ Phone Number Must Be 10 Digits</h2>" +
                            "<div style='text-align:center;'>" +
                            "<a href='contact-form.jsp'>Go Back</a>" +
                            "</div>"
            );
            return;
        }
        HttpSession session = request.getSession();

        List<Contact> contacts =
                (List<Contact>) session.getAttribute("contacts");

        if (contacts == null) {
            contacts = new ArrayList<>();
        }

        contacts.add(new Contact(name, email, phone));

        session.setAttribute("contacts", contacts);

        response.sendRedirect("contact-list.jsp");
    }
}